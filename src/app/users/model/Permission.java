package app.users.model;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;

public enum Permission {
    VIEW_PRODUCTS,
    MANAGE_CART,
    PLACE_ORDER,
    VIEW_OWN_ORDERS,
    VIEW_USERS,
    VIEW_ALL_ORDERS,
    VIEW_ORDER_DETAILS,
    MANAGE_PRODUCTS,
    GRANT_EXTRA_PERMISSIONS;

    public static Permission fromText(String value) {
        return Permission.valueOf(value.trim().toUpperCase(Locale.ROOT));
    }

    public static Set<Permission> parseList(String value) {
        if (value == null || value.isBlank() || value.equalsIgnoreCase("NONE")) {
            return EnumSet.noneOf(Permission.class);
        }

        Set<Permission> permissions = EnumSet.noneOf(Permission.class);
        Arrays.stream(value.split("\\|"))
                .map(String::trim)
                .filter(token -> !token.isEmpty())
                .map(Permission::fromText)
                .forEach(permissions::add);
        return permissions;
    }
}
