package app.view;

import app.users.model.Permission;
import app.users.model.User;

public abstract class AuthorizedView<T extends User> extends BaseView {

    protected final T user;

    protected AuthorizedView(T user) {
        super();
        this.user = user;
        validatePermissions();
    }

    protected abstract void validatePermissions();

    protected boolean hasPermission(Permission permission) {
        return user.hasPermission(permission);
    }

    protected boolean requirePermission(Permission permission) {
        if (!hasPermission(permission)) {
            deny();
            return false;
        }
        return true;
    }

    protected void deny() {
        System.out.println("Nu aveti permisiunea pentru aceasta actiune.");
    }
}
