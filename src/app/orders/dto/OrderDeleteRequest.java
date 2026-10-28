package app.orders.dto;

public class OrderDeleteRequest {
    private final int orderId;

    public OrderDeleteRequest(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }
}
