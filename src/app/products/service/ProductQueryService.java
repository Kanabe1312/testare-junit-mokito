package app.products.service;

import app.products.dto.ProductDto;
import app.products.dto.ProductFindByNameRequest;
import app.products.dto.ProductFindByNameResponse;
import app.products.dto.ProductListResponse;
import app.products.exceptions.ProductNotFoundException;
import app.products.model.Product;
import app.system.utils.Data;

import java.util.List;

public interface ProductQueryService extends Data {

    void showProducts();

    void afisare();

    ProductListResponse findAllProducts();

    List<ProductDto> findAllProductDtos();

    ProductFindByNameResponse findProduct(ProductFindByNameRequest request) throws ProductNotFoundException;

    ProductDto findProductDtoByName(String name) throws ProductNotFoundException;

    Product findProductByName(String name) throws ProductNotFoundException;

    boolean foundProduct(Product product);
}
