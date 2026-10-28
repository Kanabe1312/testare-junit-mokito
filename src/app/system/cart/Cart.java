package app.system.cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private final List<CartItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public void addToCart(CartItem cartItem) {
        CartItem existingItem = getCartItemById(cartItem.getProduct().getId());
        if (existingItem == null) {
            cartItems.add(cartItem);
            return;
        }

        incrementQuantity(existingItem);
    }

    public void incrementQuantity(CartItem cartItem) {
        cartItem.setQuantity(cartItem.getQuantity() + 1);
    }

    public CartItem getCartItemById(int productId) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId() == productId) {
                return cartItem;
            }
        }

        return null;
    }

    public void removeFromCart(CartItem cartItem) {
        cartItems.remove(cartItem);
    }

    public void showCartItems() {
        for (CartItem cartItem : cartItems) {
            System.out.println(cartItem.describeItem());
        }
    }

    public List<CartItem> getCartItems() {
        return Collections.unmodifiableList(cartItems);
    }

    public int totalPrice() {
        int totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }

        return totalPrice;
    }
}
