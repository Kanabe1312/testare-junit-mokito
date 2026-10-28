package app.products.dto;

public class ProductFindByNameResponse {
    private final ProductDto product;

    public ProductFindByNameResponse(ProductDto product) {
        this.product = product;
    }

    public ProductDto getProduct() {
        return product;
    }
}
