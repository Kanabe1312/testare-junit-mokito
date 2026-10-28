package app.products.service;

public class ProductQueryServiceSingleton {

    private static class LazyHolder {
        private static final ProductQueryService INSTANCE = new ProductQueryServiceImpl();
    }

    private ProductQueryServiceSingleton() {

    }

    public static ProductQueryService getINSTANCE() {
        return LazyHolder.INSTANCE;
    }
}
