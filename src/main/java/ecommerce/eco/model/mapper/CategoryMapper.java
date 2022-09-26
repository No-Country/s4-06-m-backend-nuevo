package ecommerce.eco.model.mapper;

import ecommerce.eco.model.entity.Category;
import ecommerce.eco.model.request.CategoryRequest;
import ecommerce.eco.model.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CategoryMapper {

    private final ProductMapper productMapper;
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
}