package app.orders.dto;

public class OrderCreateRequest {
    private final int customerId;
    private final int amount;
    private final String shippingAddress;

    public OrderCreateRequest(int customerId, int amount, String shippingAddress) {
        this.customerId = customerId;
        this.amount = amount;
        this.shippingAddress = shippingAddress;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getAmount() {
        return amount;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }
}
