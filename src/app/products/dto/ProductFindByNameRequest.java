package app.products.dto;

public class ProductFindByNameRequest {
    private final String name;

    public ProductFindByNameRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
