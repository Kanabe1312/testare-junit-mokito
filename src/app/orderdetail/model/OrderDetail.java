package app.orderdetail.model;

public class OrderDetail {
    private int id;
    private int orderId;
    private int productId;
    private float price;
    private int quantity;

    public OrderDetail(int id, int orderId, int productId, float price, int quantity) {
        this.productId = productId;
        this.id = id;
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderDetail(int orderId, int productId, float price, int quantity) {
        this.productId = productId;
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderDetail(String text) {
        String[] tokens = text.split(",");
        this.id = Integer.parseInt(tokens[0]);
        this.orderId = Integer.parseInt(tokens[1]);
        this.productId = Integer.parseInt(tokens[2]);
        this.price = Float.parseFloat(tokens[3]);
        this.quantity = Integer.parseInt(tokens[4]);
    }

    private OrderDetail(OrderDetailBuilder builder) {
        this.id = builder.id;
        this.orderId = builder.orderId;
        this.productId = builder.productId;
        this.price = builder.price;
        this.quantity = builder.quantity;
    }

    public static class OrderDetailBuilder {
        private int id;
        private int orderId;
        private int productId;
        private float price;
        private int quantity;

        public OrderDetailBuilder id(int id) {
            this.id = id;
            return this;
        }

        public OrderDetailBuilder orderId(int orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderDetailBuilder productId(int productId) {
            this.productId = productId;
            return this;
        }

        public OrderDetailBuilder price(float price) {
            this.price = price;
            return this;
        }

        public OrderDetailBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderDetail build() {
            return new OrderDetail(this);
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return this.id + "," + this.orderId + "," + this.productId + "," + this.price + "," + this.quantity;
    }
}
