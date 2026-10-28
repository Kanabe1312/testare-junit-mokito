package app.products.dto;

import java.util.List;

public class ProductListResponse {
    private final List<ProductDto> products;

    public ProductListResponse(List<ProductDto> products) {
        this.products = products;
    }

    public List<ProductDto> getProducts() {
        return products;
    }
}
