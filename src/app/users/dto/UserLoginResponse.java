package app.users.dto;

import app.users.model.Role;

public class UserLoginResponse {
    private final int id;
    private final String fullName;
    private final String username;
    private final Role role;
    private final String message;

    public UserLoginResponse(int id, String fullName, String username, Role role, String message) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.role = role;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public String getMessage() {
        return message;
    }
}
