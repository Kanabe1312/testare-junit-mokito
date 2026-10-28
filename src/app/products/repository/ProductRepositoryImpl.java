package app.products.repository;

import app.products.model.Product;
import app.system.utils.DataPaths;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProductRepositoryImpl implements ProductRepository {

    private final List<Product> products;
    String filename;

    public ProductRepositoryImpl() {
        products = new ArrayList<>();
        filename = DataPaths.of("src", "app", "products", "data", "Products.txt");
        this.loadData();
    }

    public void loadData() {
        products.clear();
        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Product product = new Product(line);
                products.add(product);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveData() {
        File file = new File(filename);
        try {
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(this);
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Product> findProductsById(int id) {

        return products.stream().filter(product -> product.getId() == id).findAny();
    }

    @Override
    public Optional<Product> findProductById(int id) {

        return products.stream().filter(product -> product.getId() == id).findAny();
    }

    @Override
    public Optional<Product> findProductByName(String name) {

        return products.stream().filter(product -> product.getName().equals(name)).findAny();
    }

    @Override
    public boolean foundProduct(Product product) {

        if (product == null){
            return false;
        }else return products.stream().anyMatch(p -> p.equals(product));
    }

    @Override
    public List<Product> listProducts() {

        return products;
    }

    @Override
    public void addProduct(Product product) {
        product.setId(generateUniqueProductId());
        products.add(product);
        saveData();
    }

    @Override
    public void updateProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == product.getId()) {
                products.set(i, product);
                saveData();
                return;
            }
        }
    }

    @Override
    public void removeProduct(Product product) {
        products.removeIf(p -> p.getId() == product.getId());
        saveData();
    }

    @Override
    public String toString() {

        StringBuilder text;
        text = new StringBuilder();

        for(Product p : products){
            text.append(p).append("\n");
        }
        return text.toString().trim();
    }

    private int generateUniqueProductId() {
        int id = 1;
        while (findProductById(id).isPresent()) {
            id++;
        }
        return id;
    }
}
