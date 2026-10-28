package app.products.dto;

public class ProductDto {
    private final int id;
    private final String name;
    private final int price;
    private final int weight;
    private final String category;
    private final int stock;

    public ProductDto(int id, String name, int price, int weight, String category, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.category = category;
        this.stock = stock;
    }

    public int getId() {
        return id;
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
