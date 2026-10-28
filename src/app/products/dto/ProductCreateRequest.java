package app.products.dto;

public class ProductCreateRequest {
    private final String name;
    private final int price;
    private final int weight;
    private final String category;
    private final int stock;

    public ProductCreateRequest(String name, int price, int weight, String category, int stock) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.category = category;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public String getCategory() {
        return category;
    }

    public int getStock() {
        return stock;
    }
}
