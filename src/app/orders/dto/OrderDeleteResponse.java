package app.orders.dto;

public class OrderDeleteResponse {
    private final int orderId;
    private final String message;

    public OrderDeleteResponse(int orderId, String message) {
        this.orderId = orderId;
        this.message = message;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getMessage() {
        return message;
    }
}
