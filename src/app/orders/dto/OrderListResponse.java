package app.orders.dto;

import java.util.List;

public class OrderListResponse {
    private final List<OrderDto> orders;

    public OrderListResponse(List<OrderDto> orders) {
        this.orders = orders;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }
}
