package app.view;

import app.users.dto.UserCreateRequest;
import app.users.dto.UserCreateResponse;
import app.users.dto.UserLoginRequest;
import app.users.dto.UserLoginResponse;
import app.users.exceptions.UserNotFoundException;
import app.users.model.Admin;
import app.users.model.Customer;
import app.users.model.Role;
import app.users.model.User;
import app.users.service.UserCommandService;
import app.users.service.UserCommandServiceSingleton;
import app.users.service.UserQueryService;
import app.users.service.UserQueryServiceSingleton;

public class ViewLogin extends BaseView {
    private static final int LOGIN_OPTION = 1;
    private static final int REGISTER_OPTION = 2;

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public ViewLogin() {
        this.userCommandService = UserCommandServiceSingleton.getINSTANCE();
        this.userQueryService = UserQueryServiceSingleton.getINSTANCE();
        runLoop();
    }

    @Override
    protected void showMenu() {
        System.out.println(LOGIN_OPTION + ". Login");
        System.out.println(REGISTER_OPTION + ". Register");
    }

    @Override
    protected boolean handleOption(int option) {
        switch (option) {
            case LOGIN_OPTION:
                login();
                return true;
            case REGISTER_OPTION:
                register();
                return true;
            default:
                System.out.println("Invalid option!");
                return true;
        }
    }

    private void login() {
        String username = readRequiredText("Username: ");
        String password = readRequiredText("Password: ");

        try {
            User authenticatedUser = userQueryService.loginUser(username, password);
            UserLoginResponse response = userQueryService.login(new UserLoginRequest(username, password));

            System.out.println(response.getMessage() + ": " + response.getUsername());

            openDashboard(response.getRole(), authenticatedUser);
        } catch (UserNotFoundException userNotFoundException) {
            System.out.println(userNotFoundException.getMessage());
        }
    }

    private void register() {
        UserCreateRequest request = new UserCreateRequest(
                readRequiredText("Introduceti numele complet: "),
                readRequiredText("Introduceti adresa de email: "),
                readRequiredText("Introduceti un username: "),
                readRequiredText("Introduceti o parola: "),
                Role.CUSTOMER,
                null,
                null,
                null,
                null,
                null
        );

        try {
            UserCreateResponse response = userCommandService.createUser(request);
            System.out.println(response.getMessage() + ": " + response.getUsername());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private String readRequiredText(String prompt) {
        System.out.println(prompt);
        return readLine();
    }

    private void openDashboard(Role role, User authenticatedUser) {
        if (role == Role.ADMIN) {
            new ViewAdmin((Admin) authenticatedUser);
            return;
        }

        if (role == Role.CUSTOMER) {
            new ViewCustomer((Customer) authenticatedUser);
            return;
        }

        System.out.println("Invalid Credentials!");
    }
}
