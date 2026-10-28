package app.users.factory;

import app.users.dto.UserCreateRequest;
import app.users.model.Admin;
import app.users.model.Customer;
import app.users.model.Role;
import app.users.model.User;

public class UserFactoryImpl implements UserFactory {

    @Override
    public User createFromText(String text) {
        String[] tokens = text.split(",");
        String type = tokens[0];

        return switch (type) {
            case "CUSTOMER" -> new Customer(text);
            case "ADMIN" -> new Admin(text);
            default -> throw new IllegalArgumentException("Unknown user type: " + type);
        };
    }

    @Override
    public User createFromRequest(UserCreateRequest request) {
        if (request.getRole() == Role.ADMIN) {
            return createAdmin(
                    request.getFullName(),
                    request.getEmail(),
                    request.getUsername(),
                    request.getPassword(),
                    request.getAdminName(),
                    request.getAdminPassword()
            );
        }

        Customer customer = (Customer) createCustomer(
                request.getFullName(),
                request.getEmail(),
                request.getUsername(),
                request.getPassword()
        );
        customer.setDefaultShippingAddress(request.getDefaultShippingAddress());
        customer.setCountry(request.getCountry());
        customer.setPhone(request.getPhone());
        return customer;
    }

    @Override
    public User createCustomer(String fullName, String email, String username, String password) {
        return new Customer.CustomerBuilder()
                .fullName(fullName)
                .email(email)
                .username(username)
                .password(password)
                .build();
    }

    @Override
    public User createAdmin(String fullName, String email, String username, String password, String adminName, String adminPassword) {
        return new Admin.AdminBuilder()
                .fullName(fullName)
                .email(email)
                .username(username)
                .password(password)
                .adminName(adminName)
                .adminPassword(adminPassword)
                .build();
    }
}
