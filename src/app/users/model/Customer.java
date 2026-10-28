package app.users.model;

public class Customer extends User {
    private String defaultShippingAddress;
    private String country;
    private String phone;

    public Customer(int id, String fullName, String email, String username, String password, String defaultShippingAddress, String country, String phone) {
        super(id, fullName, email, username, password);
        this.defaultShippingAddress = defaultShippingAddress;
        this.country = country;
        this.phone = phone;
    }

    public Customer(String text) {
        super(text);
        String[] tokens = text.split(",");
        this.defaultShippingAddress = tokens[6];
        this.country = tokens[7];
        this.phone = tokens[8];
        loadExtraPermissions(tokens, 9);
    }

    private Customer(CustomerBuilder builder) {
        super(builder.id, builder.fullName, builder.email, builder.username, builder.password);
        this.defaultShippingAddress = builder.defaultShippingAddress;
        this.country = builder.country;
        this.phone = builder.phone;
        builder.extraPermissions.forEach(this::addExtraPermission);
    }

    public static class CustomerBuilder {
        private int id;
        private String fullName;
        private String email;
        private String username;
        private String password;
        private String defaultShippingAddress;
        private String country;
        private String phone;
        private java.util.Set<Permission> extraPermissions = java.util.EnumSet.noneOf(Permission.class);

        public CustomerBuilder id(int id) {
            this.id = id;
            return this;
        }

        public CustomerBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public CustomerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder username(String username) {
            this.username = username;
            return this;
        }

        public CustomerBuilder password(String password) {
            this.password = password;
            return this;
        }

        public CustomerBuilder defaultShippingAdress(String defaultShippingAddress) {
            this.defaultShippingAddress = defaultShippingAddress;
            return this;
        }

        public CustomerBuilder country(String country) {
            this.country = country;
            return this;
        }

        public CustomerBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public CustomerBuilder extraPermissions(java.util.Set<Permission> extraPermissions) {
            this.extraPermissions = extraPermissions;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

    public void setDefaultShippingAddress(String defaultShippingAddress) {
        this.defaultShippingAddress = defaultShippingAddress;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDefaultShippingAddress() {
        return defaultShippingAddress;
    }

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public Role getRole() {
        return Role.CUSTOMER;
    }

    @Override
    public void afisare() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "CUSTOMER," + super.toString() + "," + this.defaultShippingAddress + "," + this.country + "," + this.phone + "," + serializeExtraPermissions();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Customer;
    }
}
