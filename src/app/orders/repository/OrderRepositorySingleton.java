package app.orders.repository;

public class OrderRepositorySingleton {

    private static class LazyHolder {

        private static final OrderRepository INSTANCE = new OrderRepositoryImpl();
    }

    private OrderRepositorySingleton() {

    }

    public static OrderRepository getINSTANCE() {
        return LazyHolder.INSTANCE;
    }

}
