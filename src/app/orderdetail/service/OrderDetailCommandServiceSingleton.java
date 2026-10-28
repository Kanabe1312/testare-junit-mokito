package app.orderdetail.service;

public class OrderDetailCommandServiceSingleton {

    public static class LazyHolder {

        private static final OrderDetailCommandService INSTANCE = new OrderDetailCommandServiceImpl();
    }


    private OrderDetailCommandServiceSingleton() {

    }

    public static OrderDetailCommandService getINSTANCE() {
        return LazyHolder.INSTANCE;
    }

}

