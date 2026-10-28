package app.orders.dto;

public class OrderCreateResponse {
    private final OrderDto order;
    private final String message;

    public OrderCreateResponse(OrderDto order, String message) {
        this.order = order;
        this.message = message;
    }

    public OrderDto getOrder() {
        return order;
    }

    public String getMessage() {
        return message;
    }
}
