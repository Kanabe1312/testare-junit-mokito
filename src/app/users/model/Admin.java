package app.users.model;

public class Admin extends User {
    private String adminName;
    private String adminPassword;

    public Admin(int id, String fullName, String email, String username, String password, String adminName, String adminPassword) {
        super(id, fullName, email, username, password);
        this.adminName = adminName;
        this.adminPassword = adminPassword;
    }

    public Admin(String text) {
        super(text);
        String[] tokens = text.split(",");
        this.adminName = tokens[6];
        this.adminPassword = tokens[7];
        loadExtraPermissions(tokens, 8);
    }

    private Admin(AdminBuilder builder) {
        super(builder.id, builder.fullName, builder.email, builder.username, builder.password);
        this.adminName = builder.adminName;
        this.adminPassword = builder.adminPassword;
        builder.extraPermissions.forEach(this::addExtraPermission);
    }

    public static class AdminBuilder {
        private int id;
        private String fullName;
        private String email;
        private String username;
        private String password;
        private String adminName;
        private String adminPassword;
        private java.util.Set<Permission> extraPermissions = java.util.EnumSet.noneOf(Permission.class);

        public AdminBuilder id(int id) {
            this.id = id;
            return this;
        }

        public AdminBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public AdminBuilder email(String email) {
            this.email = email;
            return this;
        }

        public AdminBuilder username(String username) {
            this.username = username;
            return this;
        }

        public AdminBuilder password(String password) {
            this.password = password;
            return this;
        }

        public AdminBuilder adminName(String adminName) {
            this.adminName = adminName;
            return this;
        }

        public AdminBuilder adminPassword(String adminPassword) {
            this.adminPassword = adminPassword;
            return this;
        }

        public AdminBuilder extraPermissions(java.util.Set<Permission> extraPermissions) {
            this.extraPermissions = extraPermissions;
            return this;
        }

        public Admin build() {
            return new Admin(this);
        }
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }

    @Override
    public void afisare() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "ADMIN," + super.toString() + "," + this.adminName + "," + this.adminPassword + "," + serializeExtraPermissions();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Admin;
    }
}
