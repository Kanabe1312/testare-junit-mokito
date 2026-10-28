package app.users.dto;

import app.users.model.Permission;
import app.users.model.Role;

import java.util.Set;

public class UserDto {
    private final int id;
    private final String fullName;
    private final String email;
    private final String username;
    private final Role role;
    private final Set<Permission> extraPermissions;
    private final Set<Permission> effectivePermissions;

    public UserDto(int id, String fullName, String email, String username, Role role,
                   Set<Permission> extraPermissions, Set<Permission> effectivePermissions) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.role = role;
        this.extraPermissions = extraPermissions;
        this.effectivePermissions = effectivePermissions;
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

    public Role getRole() {
        return role;
    }

    public Set<Permission> getExtraPermissions() {
        return extraPermissions;
    }

    public Set<Permission> getEffectivePermissions() {
        return effectivePermissions;
    }
}
