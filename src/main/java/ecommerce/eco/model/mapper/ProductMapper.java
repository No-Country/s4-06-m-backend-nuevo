package ecommerce.eco.model.mapper;

import ecommerce.eco.model.entity.Product;
import ecommerce.eco.model.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponse entityToDto(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .brand(product.getBrand())
                .color(product.getColor())
                .price(product.getPrice())
                .score(product.getScore())
                .size(product.getSize())
                .offer(product.isOffer())
                .description(product.getDescription())
                .build();
    }
}
