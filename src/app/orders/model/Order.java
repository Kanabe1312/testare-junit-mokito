package app.orders.model;

public class Order {
    private int id;
    private int customerId;
    private int ammount;
    private String shippingAddres;


    //Constructor
    public Order(int id, int customerId, int ammount, String shippingAddres) {
        this.id = id;
        this.customerId = customerId;
        this.ammount = ammount;
        this.shippingAddres = shippingAddres;
    }
    public Order(int customerId, int ammount, String shippingAddres) {

        this.customerId = customerId;
        this.ammount = ammount;
        this.shippingAddres = shippingAddres;
    }

    //Constructor cu String
    public Order(String text){
        String [] tokens = text.split(",");
        this.id = Integer.parseInt(tokens[0]);
        this.customerId = Integer.parseInt(tokens[1]);
        this.ammount = Integer.parseInt(tokens[2]);
        this.shippingAddres = tokens[3];
    }

    private Order(OrderBuilder builder){
        this.id = builder.id;
        this.customerId = builder.customerId;
        this.ammount = builder.ammount;
        this.shippingAddres = builder.shippingAddres;
    }

    public static class OrderBuilder {
        private int id;
        private int customerId;
        private int ammount;
        private String shippingAddres;

        public OrderBuilder id(int id) {
            this.id = id;
            return this;
        }

        public OrderBuilder customerId(int customerId){
            this.customerId = customerId;
            return this;
        }

        public OrderBuilder ammount(int ammount) {
            this.ammount = ammount;
            return this;
        }

        public OrderBuilder shippingAddres(String shippingAddres) {
            this.shippingAddres = shippingAddres;
            return this;
        }

        public Order build() {
            return new Order(this);

        }
    }


    //Setters
    public void setId(int id) { this.id = id; }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }
    public void setShippingAddres(String shippingAddres) {
        this.shippingAddres = shippingAddres;
    }

    //Getters
    public int getId() { return id; }
    public int getCustomerId() {
        return customerId;
    }
    public int getAmmount() {
        return ammount;
    }
    public String getShippingAddres() {
        return shippingAddres;
    }

    @Override
    public String toString() {
        return this.id + "," + this.customerId + "," + this.ammount + "," + this.shippingAddres;
    }





}
