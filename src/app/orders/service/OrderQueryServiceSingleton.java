package app.orders.service;

public class OrderQueryServiceSingleton {

    private static class LazyHolder {
        private static final OrderQueryService INSTANCE = new OrderQueryServiceImpl();
    }

    private OrderQueryServiceSingleton() {

    }

    public static OrderQueryService getINSTANCE() {
        return LazyHolder.INSTANCE;
    }


}
