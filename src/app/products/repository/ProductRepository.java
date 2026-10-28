package app.products.repository;

import app.products.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void loadData();

    void saveData();

    Optional<Product> findProductsById(int id);

    Optional<Product> findProductById(int id);

    Optional<Product> findProductByName(String name);

    boolean foundProduct(Product product);

    List<Product> listProducts();

    void addProduct(Product product);

    void updateProduct(Product product);

    void removeProduct(Product product);

}
