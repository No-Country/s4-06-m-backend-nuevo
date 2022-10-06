package ecommerce.eco.model.mapper;

import ecommerce.eco.model.entity.Product;
import ecommerce.eco.model.response.ProductDiscountResponse;
import ecommerce.eco.model.entity.User;
import ecommerce.eco.model.request.ProductRequest;
import ecommerce.eco.model.response.ProductLightningDealResponse;
import ecommerce.eco.model.response.ProductResponse;
import ecommerce.eco.service.abstraction.CategoryService;
import ecommerce.eco.service.abstraction.ColorService;
import ecommerce.eco.service.abstraction.SizeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductMapper {
   @Autowired
   private  ImageMapper imageMapper;
    @Autowired
   private  ColorMapper colorMapper;
    @Autowired
    private SizeMapper sizeMapper;
    @Autowired
    private  ColorService colorService;
    @Autowired
    private   SizeService sizeService;
    @Autowired
    private  CategoryService categoryService;

    public ProductResponse entityToDto(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .brand(product.getBrand())
                .price(product.getPrice())
                .details(product.getDetails())
                .title(product.getTitle())
                .shortDetails(product.getShortDetails())
                .sizes(product.getSizes().stream()
                        .map(sizeMapper::entityToDto)
                        .collect(Collectors.toList()))
                .colors(product.getColors().stream()
                        .map(colorMapper::entityToDto)
                        .collect(Collectors.toList()))
                .stock(product.isStock())
                .stars(product.getStars())
                .category(product.getCategory().getDescription())
                .imgList(product.getCarrousel().stream()
                        .map(imageMapper::imageToDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public Product dtoToProduct(ProductRequest request, User user) {
        return Product.builder()
                .shortDetails(request.getShortDetails())
                .details(request.getDetails())
                .title(request.getTitle())
                .brand(request.getBrand())
                .colors(colorService.stringToEnty(request.getColors()))
                .sizes(sizeService.stringToEnty(request.getSizes()))
                .cart(null)
                .category(categoryService.findById(request.getCategoryId()))
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
