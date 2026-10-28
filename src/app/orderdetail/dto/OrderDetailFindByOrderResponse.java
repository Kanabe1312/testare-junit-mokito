package app.orderdetail.dto;

import java.util.List;

public class OrderDetailFindByOrderResponse {
    private final List<OrderDetailDto> orderDetails;

    public OrderDetailFindByOrderResponse(List<OrderDetailDto> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<OrderDetailDto> getOrderDetails() {
        return orderDetails;
    }
}
