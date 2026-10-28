package app.view;

import app.users.model.Admin;
import app.users.model.Permission;

import java.util.List;

public class ViewAdmin extends AbstractUserView<Admin> {

    public ViewAdmin(Admin user) {
        super(user);
    }

    @Override
    protected List<MenuAction> buildMenuActions() {
        List<MenuAction> actions = startMenu();
        actions.add(action(1, "Lista useri", Permission.VIEW_USERS, this::showAllUsers));
        actions.add(action(2, "Lista clienti", Permission.VIEW_USERS, this::showCustomers));
        actions.add(action(3, "Lista produse", Permission.VIEW_PRODUCTS, this::showProducts));
        actions.add(action(4, "Lista toate comenzile", Permission.VIEW_ALL_ORDERS, this::showAllOrders));
        actions.add(action(5, "Detalii comanda dupa id", Permission.VIEW_ORDER_DETAILS, this::showOrderDetailsById));
        actions.add(action(6, "Adauga produs", Permission.MANAGE_PRODUCTS, this::createProduct));
        actions.add(action(7, "Update produs", Permission.MANAGE_PRODUCTS, this::updateProduct));
        actions.add(action(8, "Sterge produs", Permission.MANAGE_PRODUCTS, this::deleteProduct));
        actions.add(action(17, "Adauga permisiune extra unui user", Permission.GRANT_EXTRA_PERMISSIONS, this::grantExtraPermission));
        actions.add(action(18, "Lista permisiuni disponibile", Permission.GRANT_EXTRA_PERMISSIONS, this::showAvailablePermissions));
        return actions;
    }
}
