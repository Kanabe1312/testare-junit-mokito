package app.products.dto;

public class ProductDeleteResponse {
    private final int id;
    private final String message;

    public ProductDeleteResponse(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
