package app.products.repository;

public class ProductRepositorySingleton {

    private static class LazyHolder {
        private static final ProductRepository INSTANCE = new ProductRepositoryImpl();

    }

    private ProductRepositorySingleton() {

    }

    public static ProductRepository getINSTANCE() {
        return LazyHolder.INSTANCE;
    }
}
