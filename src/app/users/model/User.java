package app.users.model;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class User {
    private int id;
    private String fullName;
    private String email;
    private String username;
    private String password;
    private final Set<Permission> extraPermissions;

    public User(int id, String fullName, String email, String username, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.extraPermissions = EnumSet.noneOf(Permission.class);
    }

    private User(UserBuilder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.email = builder.email;
        this.username = builder.username;
        this.password = builder.password;
        this.extraPermissions = EnumSet.copyOf(builder.extraPermissions);
    }

    public static class UserBuilder {
        private int id;
        private String fullName;
        private String email;
        private String username;
        private String password;
        private Set<Permission> extraPermissions = EnumSet.noneOf(Permission.class);

        public UserBuilder id(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder extraPermissions(Set<Permission> extraPermissions) {
            this.extraPermissions = extraPermissions == null
                    ? EnumSet.noneOf(Permission.class)
                    : EnumSet.copyOf(extraPermissions);
            return this;
        }
    }

    public User(String text) {
        String[] tokens = text.split(",");
        this.id = Integer.parseInt(tokens[1]);
        this.fullName = tokens[2];
        this.email = tokens[3];
        this.username = tokens[4];
        this.password = tokens[5];
        this.extraPermissions = EnumSet.noneOf(Permission.class);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public abstract Role getRole();

    public boolean hasPermission(Permission permission) {
        return getRole().hasPermission(permission) || extraPermissions.contains(permission);
    }

    public boolean addExtraPermission(Permission permission) {
        if (getRole().hasPermission(permission)) {
            return false;
        }

        return extraPermissions.add(permission);
    }

    public Set<Permission> getExtraPermissions() {
        return EnumSet.copyOf(extraPermissions);
    }

    public Set<Permission> getAllPermissions() {
        Set<Permission> permissions = EnumSet.copyOf(getRole().getPermissions());
        permissions.addAll(extraPermissions);
        return permissions;
    }

    protected void loadExtraPermissions(String[] tokens, int index) {
        if (tokens.length <= index) {
            return;
        }

        extraPermissions.addAll(Permission.parseList(tokens[index]));
    }

    protected String serializeExtraPermissions() {
        if (extraPermissions.isEmpty()) {
            return "NONE";
        }

        return extraPermissions.stream()
                .map(Enum::name)
                .sorted()
                .collect(Collectors.joining("|"));
    }

    @Override
    public String toString() {
        return this.id + "," + this.fullName + "," + this.email + "," + this.username + "," + this.password;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof User;
    }

    public void afisare() {
        System.out.println(this);
    }
}
