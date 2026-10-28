package app.orders.service;

public class OrderCommandServiceSingleton {

    private static class LazyHolder {

        private static final OrderCommandService INSTANCE = new OrderCommandServiceImpl();
    }

    private OrderCommandServiceSingleton() {

    }

    public static OrderCommandService getINSTANCE() {
        return LazyHolder.INSTANCE;
    }

}
