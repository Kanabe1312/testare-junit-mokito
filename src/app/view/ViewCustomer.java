package app.view;

import app.users.model.Customer;
import app.users.model.Permission;

import java.util.List;

public class ViewCustomer extends AbstractUserView<Customer> {

    public ViewCustomer(Customer user) {
        super(user);
    }

    @Override
    protected List<MenuAction> buildMenuActions() {
        List<MenuAction> actions = startMenu();
        actions.add(action(3, "Lista produse", Permission.VIEW_PRODUCTS, this::showProducts));
        actions.add(action(9, "Adauga produs in cos", this::canManageCartAndViewProducts, this::addProductToCart));
        actions.add(action(10, "Editeaza cosul", Permission.MANAGE_CART, this::updateCartItemQuantity));
        actions.add(action(11, "Trimite comanda", Permission.PLACE_ORDER, this::placeOrder));
        actions.add(action(12, "Sterge un produs din cos", Permission.MANAGE_CART, this::removeProductFromCart));
        actions.add(action(13, "Afiseaza produsele din cos", Permission.MANAGE_CART, this::showCart));
        actions.add(action(14, "Afiseaza comenzile mele", Permission.VIEW_OWN_ORDERS, this::showOwnOrders));
        actions.add(action(15, "Afiseaza detaliile unei comenzi proprii", Permission.VIEW_OWN_ORDERS, this::showOwnOrderDetails));
        actions.add(action(16, "Afiseaza id-urile comenzilor mele", Permission.VIEW_OWN_ORDERS, this::showOrderIds));
        return actions;
    }
}
