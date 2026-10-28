package app.users.service;

public class UserCommandServiceSingleton {

    private static class LazyHolder {
        private static final UserCommandService INSTANCE = new UserCommandServiceImpl();
    }

    private UserCommandServiceSingleton() {

    }

    public static UserCommandService getINSTANCE() {
        return LazyHolder.INSTANCE;
    }

}
