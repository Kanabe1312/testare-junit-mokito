package app.view;

import app.orderdetail.dto.OrderDetailCreateRequest;
import app.orderdetail.dto.OrderDetailCreateResponse;
import app.orderdetail.dto.OrderDetailDto;
import app.orderdetail.dto.OrderDetailFindByOrderRequest;
import app.orderdetail.dto.OrderDetailFindByOrderResponse;
import app.orderdetail.service.OrderDetailCommandService;
import app.orderdetail.service.OrderDetailCommandServiceSingleton;
import app.orderdetail.service.OrderDetailQueryService;
import app.orderdetail.service.OrderDetailQueryServiceSingleton;
import app.orders.dto.OrderCreateRequest;
import app.orders.dto.OrderCreateResponse;
import app.orders.dto.OrderDto;
import app.orders.dto.OrderFindByCustomerRequest;
import app.orders.dto.OrderFindByCustomerResponse;
import app.orders.service.OrderCommandService;
import app.orders.service.OrderCommandServiceSingleton;
import app.orders.service.OrderQueryService;
import app.orders.service.OrderQueryServiceSingleton;
import app.products.dto.ProductCreateRequest;
import app.products.dto.ProductCreateResponse;
import app.products.dto.ProductDeleteRequest;
import app.products.dto.ProductDto;
import app.products.dto.ProductFindByNameRequest;
import app.products.dto.ProductFindByNameResponse;
import app.products.dto.ProductListResponse;
import app.products.dto.ProductUpdateRequest;
import app.products.dto.ProductUpdateResponse;
import app.products.exceptions.ProductAlreadyExistsException;
import app.products.exceptions.ProductNotFoundException;
import app.products.model.Product;
import app.products.service.ProductCommandService;
import app.products.service.ProductCommandServiceSingleton;
import app.products.service.ProductQueryService;
import app.products.service.ProductQueryServiceSingleton;
import app.system.cart.Cart;
import app.system.cart.CartItem;
import app.users.dto.UserDto;
import app.users.dto.UserListResponse;
import app.users.dto.UserPermissionGrantResponse;
import app.users.exceptions.UserNotFoundException;
import app.users.model.Permission;
import app.users.model.User;
import app.users.service.UserCommandService;
import app.users.service.UserCommandServiceSingleton;
import app.users.service.UserQueryService;
import app.users.service.UserQueryServiceSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public abstract class AbstractUserView<T extends User> extends AuthorizedView<T> {
    private static final int LOGOUT_OPTION = 0;
    private static final String DEFAULT_SHIPPING_ADDRESS = "ShippingAddres";

    protected final UserQueryService userQueryService;
    protected final UserCommandService userCommandService;
    protected final ProductQueryService productQueryService;
    protected final ProductCommandService productCommandService;
    protected final OrderQueryService orderQueryService;
    protected final OrderCommandService orderCommandService;
    protected final OrderDetailQueryService orderDetailQueryService;
    protected final OrderDetailCommandService orderDetailCommandService;
    protected final Cart cart;

    private final List<MenuAction> menuActions;

    protected AbstractUserView(T user) {
        super(user);
        this.userQueryService = UserQueryServiceSingleton.getINSTANCE();
        this.userCommandService = UserCommandServiceSingleton.getINSTANCE();
        this.productQueryService = ProductQueryServiceSingleton.getINSTANCE();
        this.productCommandService = ProductCommandServiceSingleton.getINSTANCE();
        this.orderQueryService = OrderQueryServiceSingleton.getINSTANCE();
        this.orderCommandService = OrderCommandServiceSingleton.getINSTANCE();
        this.orderDetailQueryService = OrderDetailQueryServiceSingleton.getINSTANCE();
        this.orderDetailCommandService = OrderDetailCommandServiceSingleton.getINSTANCE();
        this.cart = new Cart();
        this.menuActions = buildMenuActions();
        runLoop();
    }

    @Override
    protected void showMenu() {
        printCurrentUserSummary();
        printMenuOption(LOGOUT_OPTION, "Logout");

        for (MenuAction action : menuActions) {
            if (action.isVisible()) {
                printMenuOption(action.option(), action.label());
            }
        }
    }

    @Override
    protected boolean handleOption(int option) {
        if (option == LOGOUT_OPTION) {
            return false;
        }

        MenuAction action = findAction(option);
        if (action == null) {
            printInvalidOption();
            return true;
        }

        if (!action.isVisible()) {
            deny();
            return true;
        }

        action.execute();
        return true;
    }

    @Override
    protected void validatePermissions() {
        if (user.getAllPermissions().isEmpty()) {
            throw new IllegalStateException("User has no permissions assigned.");
        }
    }

    protected final List<MenuAction> startMenu() {
        return new ArrayList<>();
    }

    protected final MenuAction action(int option, String label, Permission permission, Runnable handler) {
        return action(option, label, () -> hasPermission(permission), handler);
    }

    protected final MenuAction action(int option, String label, BooleanSupplier visibilityRule, Runnable handler) {
        return new MenuAction(option, label, visibilityRule, handler);
    }

    protected abstract List<MenuAction> buildMenuActions();

    protected void showAllUsers() {
        printUsers(userQueryService.findAllUsers());
    }

    protected void showCustomers() {
        printUsers(userQueryService.findCustomers());
    }

    protected void showProducts() {
        printProducts(productQueryService.findAllProducts());
    }

    protected void showAllOrders() {
        printOrders(orderQueryService.findAllOrders().getOrders());
    }

    protected void showOrderDetailsById() {
        printOrderDetails(readIntWithPrompt("Introdu id-ul comenzii: "));
    }

    protected void createProduct() {
        try {
            ProductCreateRequest request = new ProductCreateRequest(
                    readText("Nume produs:"),
                    readIntWithPrompt("Pret:"),
                    readIntWithPrompt("Greutate:"),
                    readText("Categorie:"),
                    readIntWithPrompt("Stoc:")
            );
            ProductCreateResponse response = productCommandService.createProduct(request);
            System.out.println(response.getMessage() + ": " + response.getProduct().getName());
        } catch (ProductAlreadyExistsException | IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    protected void updateProduct() {
        printProducts(productQueryService.findAllProducts());

        try {
            ProductUpdateRequest request = new ProductUpdateRequest(
                    readIntWithPrompt("Id produs:"),
                    readText("Nume nou:"),
                    readIntWithPrompt("Pret nou:"),
                    readIntWithPrompt("Greutate noua:"),
                    readText("Categorie noua:"),
                    readIntWithPrompt("Stoc nou:")
            );
            ProductUpdateResponse response = productCommandService.updateProduct(request);
            System.out.println(response.getMessage() + ": " + response.getProduct().getName());
        } catch (ProductNotFoundException | ProductAlreadyExistsException | IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    protected void deleteProduct() {
        printProducts(productQueryService.findAllProducts());

        try {
            int productId = readIntWithPrompt("Id produs:");
            System.out.println(productCommandService.deleteProduct(new ProductDeleteRequest(productId)).getMessage());
        } catch (ProductNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

    protected void addProductToCart() {
        printProducts(productQueryService.findAllProducts());

        ProductDto selectedProduct = promptProductByName("Introduceti numele produsului dorit: ");
        if (selectedProduct == null) {
            return;
        }

        cart.addToCart(new CartItem(toProduct(selectedProduct), 1));
        System.out.println("Produsul a fost adaugat in cos.");
    }

    protected void updateCartItemQuantity() {
        CartItem cartItem = promptExistingCartItem("Introduceti numele produsului pe care doriti sa-l editati: ");
        if (cartItem == null) {
            return;
        }

        cartItem.setQuantity(readIntWithPrompt("Introduceti cantitatea dorita: "));
        System.out.println("Cantitatea a fost actualizata.");
    }

    protected void placeOrder() {
        if (cart.getCartItems().isEmpty()) {
            System.out.println("Cosul este gol.");
            return;
        }

        showCart();
        if (!confirmOrderPlacement()) {
            System.out.println("Comanda nu va fi trimisa ");
            return;
        }

        OrderCreateResponse orderResponse = orderCommandService.createOrder(
                new OrderCreateRequest(user.getId(), cart.totalPrice(), DEFAULT_SHIPPING_ADDRESS)
        );

        createOrderDetails(orderResponse.getOrder().getId());
        System.out.println("Comanda cu Id-ul:" + orderResponse.getOrder().getId() + " a fost trimisa cu succes");
    }

    protected void removeProductFromCart() {
        CartItem cartItem = promptExistingCartItem("Introduceti numele produsului pe care doriti sa-l stergeti din cos: ");
        if (cartItem == null) {
            return;
        }

        cart.removeFromCart(cartItem);
        System.out.println("Produsul " + cartItem.getProduct().getName() + " a fost sters!");
    }

    protected void showCart() {
        System.out.println("Cosul dumneavoastra contine: ");
        cart.showCartItems();
    }

    protected void showOwnOrders() {
        System.out.println("Comenzile utilizatorului sunt: ");
        printOrders(getOwnOrders().getOrders());
    }

    protected void showOwnOrderDetails() {
        int orderId = readIntWithPrompt("Introdu Id-ul comenzii tale: ");
        if (!userOwnsOrder(orderId)) {
            System.out.println("Comanda nu apartine utilizatorului curent.");
            return;
        }

        printOrderDetails(orderId);
    }

    protected void showOrderIds() {
        for (OrderDto order : getOwnOrders().getOrders()) {
            System.out.println(order.getId());
        }
    }

    protected void grantExtraPermission() {
        showAllUsers();
        showAvailablePermissions();

        try {
            int userId = readIntWithPrompt("Introdu id-ul userului:");
            Permission permission = Permission.fromText(readText("Introdu numele exact al permisiunii:"));
            UserPermissionGrantResponse response = userCommandService.grantExtraPermission(userId, permission);
            System.out.println(response.getMessage() + ": " + response.getUsername() + " -> " + response.getPermission());
        } catch (IllegalArgumentException exception) {
            System.out.println("Permisiune invalida.");
        } catch (UserNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

    protected void showAvailablePermissions() {
        for (Permission permission : Permission.values()) {
            System.out.println(permission.name());
        }
    }

    protected boolean canManageCartAndViewProducts() {
        return hasPermission(Permission.MANAGE_CART) && hasPermission(Permission.VIEW_PRODUCTS);
    }

    private MenuAction findAction(int option) {
        for (MenuAction action : menuActions) {
            if (action.option() == option) {
                return action;
            }
        }

        return null;
    }

    private CartItem promptExistingCartItem(String prompt) {
        showCart();
        ProductDto product = promptProductByName(prompt);
        if (product == null) {
            return null;
        }

        CartItem cartItem = cart.getCartItemById(product.getId());
        if (cartItem == null) {
            System.out.println("Produsul nu exista in cos!");
        }

        return cartItem;
    }

    private ProductDto promptProductByName(String prompt) {
        String productName = readText(prompt);

        try {
            ProductFindByNameResponse response = productQueryService.findProduct(new ProductFindByNameRequest(productName));
            return response.getProduct();
        } catch (ProductNotFoundException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    private boolean confirmOrderPlacement() {
        return readText("Doriti sa trimiteti comanda?   R:DA/NU ").equalsIgnoreCase("DA");
    }

    private OrderFindByCustomerResponse getOwnOrders() {
        return orderQueryService.findOrders(new OrderFindByCustomerRequest(user.getId()));
    }

    private boolean userOwnsOrder(int orderId) {
        return getOwnOrders().getOrders().stream().anyMatch(order -> order.getId() == orderId);
    }

    private void createOrderDetails(int orderId) {
        for (CartItem cartItem : cart.getCartItems()) {
            OrderDetailCreateResponse response = orderDetailCommandService.createOrderDetail(
                    new OrderDetailCreateRequest(
                            orderId,
                            cartItem.getProduct().getId(),
                            cartItem.getProduct().getPrice(),
                            cartItem.getQuantity()
                    )
            );
            System.out.println(response.getMessage() + ": " + response.getOrderDetail().getId());
        }
    }

    private Product toProduct(ProductDto productDto) {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getWeight(),
                productDto.getCategory(),
                productDto.getStock()
        );
    }

    private void printCurrentUserSummary() {
        System.out.println("User: " + user.getUsername() + " | Role: " + user.getRole());
        System.out.println("Permisiuni efective: " + user.getAllPermissions());
    }

    private void printMenuOption(int option, String description) {
        System.out.println(option + " -> " + description);
    }

    protected String readText(String prompt) {
        System.out.println(prompt);
        return readLine();
    }

    protected int readIntWithPrompt(String prompt) {
        System.out.println(prompt);
        return readInt();
    }

    protected void printUsers(UserListResponse response) {
        for (UserDto currentUser : response.getUsers()) {
            System.out.println(
                    "Id: " + currentUser.getId()
                            + ", Name: " + currentUser.getFullName()
                            + ", Email: " + currentUser.getEmail()
                            + ", Username: " + currentUser.getUsername()
                            + ", Role: " + currentUser.getRole()
                            + ", ExtraPermissions: " + currentUser.getExtraPermissions()
                            + ", EffectivePermissions: " + currentUser.getEffectivePermissions()
            );
        }
    }

    protected void printProducts(ProductListResponse response) {
        for (ProductDto product : response.getProducts()) {
            System.out.println(formatProduct(product));
        }
    }

    protected void printOrders(List<OrderDto> orders) {
        for (OrderDto order : orders) {
            System.out.println(formatOrder(order));
        }
    }

    protected void printOrderDetails(int orderId) {
        if (!orderQueryService.checkOrderId(orderId)) {
            System.out.println("Id invalid!");
            return;
        }

        OrderDetailFindByOrderResponse response = orderDetailQueryService.findOrderDetails(
                new OrderDetailFindByOrderRequest(orderId)
        );

        for (OrderDetailDto orderDetail : response.getOrderDetails()) {
            System.out.println(formatOrderDetail(orderDetail));
        }
    }

    private String formatProduct(ProductDto product) {
        return product.getId() + "," + product.getName() + "," + product.getPrice() + ","
                + product.getWeight() + "," + product.getCategory() + "," + product.getStock();
    }

    private String formatOrder(OrderDto order) {
        return "Comanda: " + order.getId()
                + ", customerId: " + order.getCustomerId()
                + ", amount: " + order.getAmount()
                + ", shippingAddress: " + order.getShippingAddress();
    }

    private String formatOrderDetail(OrderDetailDto orderDetail) {
        return "Id:" + orderDetail.getId()
                + " OrderId:" + orderDetail.getOrderId()
                + " ProductId:" + orderDetail.getProductId()
                + " Quantity:" + orderDetail.getQuantity();
    }

    protected static final class MenuAction {
        private final int option;
        private final String label;
        private final BooleanSupplier visibilityRule;
        private final Runnable handler;

        private MenuAction(int option, String label, BooleanSupplier visibilityRule, Runnable handler) {
            this.option = option;
            this.label = label;
            this.visibilityRule = visibilityRule;
            this.handler = handler;
        }

        private int option() {
            return option;
        }

        private String label() {
            return label;
        }

        private boolean isVisible() {
            return visibilityRule.getAsBoolean();
        }

        private void execute() {
            handler.run();
        }
    }
}
