package app.orderdetail.dto;

public class OrderDetailDeleteResponse {
    private final int orderDetailId;
    private final String message;

    public OrderDetailDeleteResponse(int orderDetailId, String message) {
        this.orderDetailId = orderDetailId;
        this.message = message;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public String getMessage() {
        return message;
    }
}
