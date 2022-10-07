package ecommerce.eco.model.mapper;

import ecommerce.eco.model.entity.Category;
import ecommerce.eco.model.request.CategoryRequest;
import ecommerce.eco.model.response.CategoryDiscountResponse;
import ecommerce.eco.model.response.CategoryLightningDealResponse;
import ecommerce.eco.model.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class CategoryMapper {
    @Autowired
    private  ProductMapper productMapper;
    public Category entityToDto(CategoryRequest request) {
        return Category.builder()
                .description(request.getDescription())
                .softDeleted(request.isSoftDeleted())
                .build();
    }

    public CategoryResponse dtoToEntity(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .description(category.getDescription())
                .products(category.getProducts().stream().map(
                                productMapper::entityToDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public CategoryResponse dtoToEntityCreate(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .description(category.getDescription())
                .products(List.of())
                .build();
    }

    public CategoryDiscountResponse dtoToEntityWithDiscount(Category category) {
        return CategoryDiscountResponse.builder()
                .id(category.getId())
                .description(category.getDescription())
                .products(category.getProducts().stream().map(
                                productMapper::entityToDtoWithDiscount)
                        .collect(Collectors.toList()))
                .build();
    }

    public CategoryLightningDealResponse dtoToEntityLightningDeal(Category category) {
        return CategoryLightningDealResponse.builder()
                .id(category.getId())
                .description(category.getDescription())
                .lightningDealResponseList(category.getProducts().stream().map(
                        productMapper::entityToDtoLightningDeal)
                        .collect(Collectors.toList()))
                .build();
    }


    public List<CategoryResponse> dtoToEntityList(List<Category> categories) {
        List<CategoryResponse> responses = new ArrayList<>();
        CategoryResponse response;
        for (Category c: categories){
            response = new CategoryResponse();
            response.setId(c.getId());
            response.setDescription(c.getDescription());
            response.setProducts(c.getProducts().stream().map(
                            productMapper::entityToDto)
                    .collect(Collectors.toList()));
            responses.add(response) ;
        }

        return responses;
    }
}
