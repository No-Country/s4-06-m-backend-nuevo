package ecommerce.eco.model.mapper;

import ecommerce.eco.model.entity.Product;
import ecommerce.eco.model.response.ProductDiscountResponse;
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
                .details(product.getDetails())
                .title(product.getTitle())
                .shortDetails(product.getShortDetails())
                .size(product.getSize())
                .build();
    }

    public ProductDiscountResponse entityToDtoWithDiscount(Product product) {
        double discount = product.discount();
        return ProductDiscountResponse.builder()
                .id(product.getId())
                .brand(product.getBrand())
                .details(product.getDetails())
                .title(product.getTitle())
                .shortDetails(product.getShortDetails())
                .priceWithDiscount(discount)
                .priceWithoutDiscount(product.getPrice())
                .discount(product.getDiscount())
                .stock(product.isStock())
                .build();
    }
}
