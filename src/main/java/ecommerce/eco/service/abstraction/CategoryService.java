package ecommerce.eco.service.abstraction;

import ecommerce.eco.model.entity.Category;
import ecommerce.eco.model.request.CategoryRequest;
import ecommerce.eco.model.response.CategoryDiscountResponse;
import ecommerce.eco.model.response.CategoryResponse;

public interface CategoryService {
    CategoryResponse create(CategoryRequest request);

    void deleted(Long id);

    CategoryResponse getCategoryAndProducts(Long id);

    CategoryResponse findByDescription(String description);

    CategoryDiscountResponse getCategoryWithDiscount(Long newOffersId);

    Category findById(Long categoryId);
}
