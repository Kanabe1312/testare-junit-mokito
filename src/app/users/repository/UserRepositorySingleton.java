package app.users.repository;

public class UserRepositorySingleton {

    private static class LazyHolder {
        private static final UserRepository INSTANCE = new UserRepositoryImpl();

    }

    private UserRepositorySingleton() {

    }

    public static UserRepository getINSTANCE() {
        return LazyHolder.INSTANCE;
    }
}
