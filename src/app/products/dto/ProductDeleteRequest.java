package app.products.dto;

public class ProductDeleteRequest {
    private final int id;

    public ProductDeleteRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
