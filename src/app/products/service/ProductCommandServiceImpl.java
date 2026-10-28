package app.products.service;

import app.products.dto.ProductCreateRequest;
import app.products.dto.ProductCreateResponse;
import app.products.dto.ProductDeleteRequest;
import app.products.dto.ProductDeleteResponse;
import app.products.dto.ProductUpdateRequest;
import app.products.dto.ProductUpdateResponse;
import app.products.exceptions.ProductAlreadyExistsException;
import app.products.exceptions.ProductNotFoundException;
import app.products.mapper.ProductMapper;
import app.products.model.Product;
import app.products.repository.ProductRepository;
import app.products.repository.ProductRepositorySingleton;

public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductCommandServiceImpl() {
        this(ProductRepositorySingleton.getINSTANCE(), new ProductMapper());
    }

    public ProductCommandServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductCreateResponse createProduct(ProductCreateRequest request) throws ProductAlreadyExistsException {
        validateProductFields(
                request.getName(),
                request.getPrice(),
                request.getWeight(),
                request.getCategory(),
                request.getStock()
        );

        Product product = new Product(
                0,
                request.getName(),
                request.getPrice(),
                request.getWeight(),
                request.getCategory(),
                request.getStock()
        );
        addProduct(product);
        return new ProductCreateResponse(productMapper.toDto(product), "Product created successfully");
    }

    @Override
    public ProductUpdateResponse updateProduct(ProductUpdateRequest request) throws ProductNotFoundException, ProductAlreadyExistsException {
        Product existingProduct = productRepository.findProductById(request.getId())
                .orElseThrow(ProductNotFoundException::new);

        validateProductFields(
                request.getName(),
                request.getPrice(),
                request.getWeight(),
                request.getCategory(),
                request.getStock()
        );
        ensureUniqueNameForUpdate(request.getId(), request.getName());

        existingProduct.setName(request.getName());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setWeight(request.getWeight());
        existingProduct.setCategory(request.getCategory());
        existingProduct.setStock(request.getStock());

        updateProduct(existingProduct);
        return new ProductUpdateResponse(productMapper.toDto(existingProduct), "Product updated successfully");
    }

    @Override
    public ProductDeleteResponse deleteProduct(ProductDeleteRequest request) throws ProductNotFoundException {
        Product product = productRepository.findProductById(request.getId())
                .orElseThrow(ProductNotFoundException::new);
        removeProduct(product);
        return new ProductDeleteResponse(request.getId(), "Product deleted successfully");
    }

    @Override
    public void addProduct(Product product) throws ProductAlreadyExistsException {
        if (productRepository.findProductByName(product.getName()).isPresent()) {
            throw new ProductAlreadyExistsException();
        }
        productRepository.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) throws ProductNotFoundException {
        if (productRepository.findProductById(product.getId()).isEmpty()) {
            throw new ProductNotFoundException();
        }
        productRepository.updateProduct(product);
    }

    @Override
    public void removeProduct(Product product) throws ProductNotFoundException {
        if (productRepository.findProductById(product.getId()).isEmpty()) {
            throw new ProductNotFoundException();
        }
        productRepository.removeProduct(product);
    }

    private void validateProductFields(String name, int price, int weight, String category, int stock) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name must not be blank");
        }
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Product category must not be blank");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Product price must not be negative");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Product weight must not be negative");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Product stock must not be negative");
        }
    }

    private void ensureUniqueNameForUpdate(int productId, String name) throws ProductAlreadyExistsException {
        Product productWithSameName = productRepository.findProductByName(name).orElse(null);
        if (productWithSameName != null && productWithSameName.getId() != productId) {
            throw new ProductAlreadyExistsException();
        }
    }
}
