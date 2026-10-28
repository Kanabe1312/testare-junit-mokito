package app.users.model;

import java.util.EnumSet;
import java.util.Set;

public enum Role {
    CUSTOMER(EnumSet.of(
            Permission.VIEW_PRODUCTS,
            Permission.MANAGE_CART,
            Permission.PLACE_ORDER,
            Permission.VIEW_OWN_ORDERS
    )),
    ADMIN(EnumSet.of(
            Permission.VIEW_USERS,
            Permission.VIEW_PRODUCTS,
            Permission.VIEW_ALL_ORDERS,
            Permission.VIEW_ORDER_DETAILS,
            Permission.MANAGE_PRODUCTS,
            Permission.GRANT_EXTRA_PERMISSIONS
    ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public boolean hasPermission(Permission permission) {
        return permissions.contains(permission);
    }

    public Set<Permission> getPermissions() {
        return EnumSet.copyOf(permissions);
    }
}
