package app.orderdetail.service;

public class OrderDetailQueryServiceSingleton {

    private static class LazyHolder {

        private static final OrderDetailQueryService INSTANCE = new OrderDetailQueryServiceImpl();
    }


    private OrderDetailQueryServiceSingleton() {

    }

    public static OrderDetailQueryService getINSTANCE() {
        return LazyHolder.INSTANCE;
    }

}
