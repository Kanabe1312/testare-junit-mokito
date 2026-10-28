package app.products.service;

public class ProductCommandServiceSingleton {

    private static class LazyHolder {
        private static final ProductCommandService INSTANCE = new ProductCommandServiceImpl();
    }

    private ProductCommandServiceSingleton() {

    }

    public static ProductCommandService getINSTANCE() {
        return LazyHolder.INSTANCE;
    }

}
