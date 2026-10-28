package app.users.factory;

public class UserFactorySingleton {

    private static class LazyHolder {
        private static final UserFactory INSTANCE = new UserFactoryImpl();
    }

    private UserFactorySingleton() {
    }

    public static UserFactory getINSTANCE() {
        return LazyHolder.INSTANCE;
    }
}
