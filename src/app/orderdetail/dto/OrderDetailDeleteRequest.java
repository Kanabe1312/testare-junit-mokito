package app.orderdetail.dto;

public class OrderDetailDeleteRequest {
    private final int orderDetailId;

    public OrderDetailDeleteRequest(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }
}
