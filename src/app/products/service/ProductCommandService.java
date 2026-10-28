package app.products.service;

import app.products.dto.ProductCreateRequest;
import app.products.dto.ProductCreateResponse;
import app.products.dto.ProductDeleteRequest;
import app.products.dto.ProductDeleteResponse;
import app.products.dto.ProductUpdateRequest;
import app.products.dto.ProductUpdateResponse;
import app.products.exceptions.ProductAlreadyExistsException;
import app.products.exceptions.ProductNotFoundException;
import app.products.model.Product;
import app.system.utils.Data;

public interface ProductCommandService extends Data {

    ProductCreateResponse createProduct(ProductCreateRequest request) throws ProductAlreadyExistsException;

    ProductUpdateResponse updateProduct(ProductUpdateRequest request) throws ProductNotFoundException, ProductAlreadyExistsException;

    ProductDeleteResponse deleteProduct(ProductDeleteRequest request) throws ProductNotFoundException;

    void addProduct(Product product) throws ProductAlreadyExistsException;

    void updateProduct(Product product) throws ProductNotFoundException;

    void removeProduct(Product product) throws ProductNotFoundException;

}
