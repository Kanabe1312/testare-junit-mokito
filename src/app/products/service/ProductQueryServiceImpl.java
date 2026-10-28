package app.products.service;

import app.products.dto.ProductDto;
import app.products.dto.ProductFindByNameRequest;
import app.products.dto.ProductFindByNameResponse;
import app.products.dto.ProductListResponse;
import app.products.exceptions.ProductNotFoundException;
import app.products.mapper.ProductMapper;
import app.products.model.Product;
import app.products.repository.ProductRepository;
import app.products.repository.ProductRepositorySingleton;

import java.util.List;
import java.util.Optional;

public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductQueryServiceImpl() {
        productRepository = ProductRepositorySingleton.getINSTANCE();
        productMapper = new ProductMapper();
    }

    @Override
    public void showProducts() {
        productRepository.listProducts().forEach(System.out::println);
    }

    @Override
    public void afisare() {
        productRepository.listProducts().forEach(System.out::println);
    }

    @Override
    public ProductListResponse findAllProducts() {
        return new ProductListResponse(findAllProductDtos());
    }

    @Override
    public List<ProductDto> findAllProductDtos() {
        return productRepository.listProducts().stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ProductDto findProductDtoByName(String name) throws ProductNotFoundException {
        return productMapper.toDto(findProductByName(name));
    }

    @Override
    public ProductFindByNameResponse findProduct(ProductFindByNameRequest request) throws ProductNotFoundException {
        return new ProductFindByNameResponse(findProductDtoByName(request.getName()));
    }

    @Override
    public Product findProductByName(String name) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findProductByName(name);

        if (product.isEmpty()) {
            throw new ProductNotFoundException();
        }

        return product.get();
    }

    @Override
    public boolean foundProduct(Product product) {
        return productRepository.foundProduct(product);
    }
}
