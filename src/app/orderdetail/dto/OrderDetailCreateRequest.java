package app.orderdetail.dto;

public class OrderDetailCreateRequest {
    private final int orderId;
    private final int productId;
    private final float price;
    private final int quantity;

    public OrderDetailCreateRequest(int orderId, int productId, float price, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
