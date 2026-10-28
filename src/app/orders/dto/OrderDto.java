package app.orders.dto;

public class OrderDto {
    private final int id;
    private final int customerId;
    private final int amount;
    private final String shippingAddress;

    public OrderDto(int id, int customerId, int amount, String shippingAddress) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.shippingAddress = shippingAddress;
    }

    public int getId() {
        return id;
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
