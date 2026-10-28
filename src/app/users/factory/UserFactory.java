package app.users.factory;

import app.users.dto.UserCreateRequest;
import app.users.model.User;

public interface UserFactory {

    User createFromText(String text);

    User createFromRequest(UserCreateRequest request);

    User createCustomer(String fullName, String email, String username, String password);

    User createAdmin(String fullName, String email, String username, String password, String adminName, String adminPassword);
}
