package app.users.dto;

import app.users.model.Role;

public class UserCreateRequest {
    private final String fullName;
    private final String email;
    private final String username;
    private final String password;
    private final Role role;
    private final String defaultShippingAddress;
    private final String country;
    private final String phone;
    private final String adminName;
    private final String adminPassword;

    public UserCreateRequest(
            String fullName,
            String email,
            String username,
            String password,
            Role role,
            String defaultShippingAddress,
            String country,
            String phone,
            String adminName,
            String adminPassword
    ) {
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.defaultShippingAddress = defaultShippingAddress;
        this.country = country;
        this.phone = phone;
        this.adminName = adminName;
        this.adminPassword = adminPassword;
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

    public Role getRole() {
        return role;
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

    public String getAdminName() {
        return adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }
}
