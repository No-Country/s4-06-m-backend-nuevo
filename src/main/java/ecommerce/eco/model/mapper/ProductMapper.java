package ecommerce.eco.model.mapper;

import ecommerce.eco.model.entity.Image;
import ecommerce.eco.model.entity.Product;
import ecommerce.eco.model.response.ProductDiscountResponse;
import ecommerce.eco.model.entity.User;
import ecommerce.eco.model.request.ProductRequest;
import ecommerce.eco.model.response.ProductLightningDealResponse;
import ecommerce.eco.model.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    public final ImageMapper imageMapper;
    private final ReviewMapper reviewMapper;
    public ProductResponse entityToDto(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .brand(product.getBrand())
                .color(product.getColor().getName())
                .price(product.getPrice())
                .details(product.getDetails())
                .title(product.getTitle())
                .shortDetails(product.getShortDetails())
                .size(product.getSize().getName())
                .stock(product.isStock())
                .imgList(product.getCarrousel().stream()
                        .map(imageMapper::imageToDto)
                        .collect(Collectors.toList()))
                .build();
    }
    public Product dtoToProduct(ProductRequest request, User user){
        return Product.builder()
                .shortDetails(request.getShortDetails())
                .details(request.getDetails())
                .title(request.getTitle())
                .brand(request.getBrand())
                .cart(null)
                .categoryId(request.getCategoryId())
                .price(request.getPrice())
                .reviews(null)
                .stock(request.isStock())
                .view(request.getView())
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
                .imgList(product.getCarrousel().stream()
                        .map(imageMapper::imageToDto)
                        .collect(Collectors.toList()))
                //.reviewResponses(product.getReviews().stream().map(reviewMapper::dtoToEntity).collect(
                //        Collectors.toList()))
                .build();
    }

    public ProductLightningDealResponse entityToDtoLightningDeal(Product product) {
        return ProductLightningDealResponse.builder()
                .id(product.getId())
                .details(product.getDetails())
                .price(product.getPrice())
                .imgList(product.getCarrousel().stream().map(
                        imageMapper::imageToDto).collect(Collectors.toList()))
                .build();
    }
}
