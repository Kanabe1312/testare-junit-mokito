package app.orders.dto;

import java.util.List;

public class OrderFindByCustomerResponse {
    private final List<OrderDto> orders;

    public OrderFindByCustomerResponse(List<OrderDto> orders) {
        this.orders = orders;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }
}
