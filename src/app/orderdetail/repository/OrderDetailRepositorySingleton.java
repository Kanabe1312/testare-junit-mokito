package app.orderdetail.repository;

public class OrderDetailRepositorySingleton {

    private static class LazyHolder {
        private static final OrderDetailRepository INSTANCE = new OrderDetailRepositoryImpl();
    }

    private OrderDetailRepositorySingleton() {
    }

    public static OrderDetailRepository getINSTANCE() {
        return LazyHolder.INSTANCE;
    }
}
