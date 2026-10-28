package app.orderdetail.dto;

public class OrderDetailFindByOrderRequest {
    private final int orderId;

    public OrderDetailFindByOrderRequest(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }
}
