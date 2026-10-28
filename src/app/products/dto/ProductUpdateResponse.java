package app.products.dto;

public class ProductUpdateResponse {
    private final ProductDto product;
    private final String message;

    public ProductUpdateResponse(ProductDto product, String message) {
        this.product = product;
        this.message = message;
    }

    public ProductDto getProduct() {
        return product;
    }

    public String getMessage() {
        return message;
    }
}
