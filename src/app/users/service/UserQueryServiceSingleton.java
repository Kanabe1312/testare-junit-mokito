package app.users.service;

public class UserQueryServiceSingleton {

    private static class LazyHolder {
        private static final UserQueryService INSTANCE = new UserQueryServiceImpl();
    }

    private UserQueryServiceSingleton() {

    }

    public static UserQueryService getINSTANCE() {
        return LazyHolder.INSTANCE;
    }
}
