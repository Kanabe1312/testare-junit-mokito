package app.orders.dto;

public class OrderFindByCustomerRequest {
    private final int customerId;

    public OrderFindByCustomerRequest(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }
}
