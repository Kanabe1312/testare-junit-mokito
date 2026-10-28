package app.orderdetail.dto;

public class OrderDetailCreateResponse {
    private final OrderDetailDto orderDetail;
    private final String message;

    public OrderDetailCreateResponse(OrderDetailDto orderDetail, String message) {
        this.orderDetail = orderDetail;
        this.message = message;
    }

    public OrderDetailDto getOrderDetail() {
        return orderDetail;
    }

    public String getMessage() {
        return message;
    }
}
