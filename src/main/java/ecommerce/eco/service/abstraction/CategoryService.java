package ecommerce.eco.service.abstraction;

import ecommerce.eco.model.entity.Category;
import ecommerce.eco.model.request.CategoryRequest;
import ecommerce.eco.model.response.CategoryDiscountResponse;
import ecommerce.eco.model.response.CategoryLightningDealResponse;
import ecommerce.eco.model.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest request);

    void deleted(Long id);

    CategoryResponse getCategoryAndProducts(Long id);

    CategoryResponse findByDescription(String description);

    CategoryDiscountResponse getCategoryWithDiscount(Long newOffersId);

    Category findById(Long categoryId);

    CategoryLightningDealResponse getCategoryLightningDeal(Long lightningDealId);

    List<CategoryResponse> getAll();

    CategoryResponse findByTitleAndDescription(String description, String title);
}
