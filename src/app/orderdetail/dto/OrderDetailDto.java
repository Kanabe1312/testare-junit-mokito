package app.orderdetail.dto;

public class OrderDetailDto {
    private final int id;
    private final int orderId;
    private final int productId;
    private final float price;
    private final int quantity;

    public OrderDetailDto(int id, int orderId, int productId, float price, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
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
