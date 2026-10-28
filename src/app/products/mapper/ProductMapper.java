package app.products.mapper;

import app.products.dto.ProductDto;
import app.products.model.Product;

public class ProductMapper {

    public ProductDto toDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getWeight(),
                product.getCategory(),
                product.getStock()
        );
    }

    public Product toModel(ProductDto productDto) {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getWeight(),
                productDto.getCategory(),
                productDto.getStock()
        );
    }
}
