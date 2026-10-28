package app.system.cart;

import app.products.model.Product;

public class CartItem {

    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String describeItem() {
        return "Name: " + product.getName() + "\n"
                + "Quantity: " + quantity + "\n"
                + "Total price: " + (product.getPrice() * quantity) + "\n";
    }
}
