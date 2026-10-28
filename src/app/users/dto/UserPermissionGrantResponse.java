package app.users.dto;

import app.users.model.Permission;

public class UserPermissionGrantResponse {
    private final int userId;
    private final String username;
    private final Permission permission;
    private final String message;

    public UserPermissionGrantResponse(int userId, String username, Permission permission, String message) {
        this.userId = userId;
        this.username = username;
        this.permission = permission;
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Permission getPermission() {
        return permission;
    }

    public String getMessage() {
        return message;
    }
}
