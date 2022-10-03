package ecommerce.eco.service;

import ecommerce.eco.model.entity.Category;
import ecommerce.eco.model.mapper.CategoryMapper;
import ecommerce.eco.model.request.CategoryRequest;
import ecommerce.eco.model.response.CategoryDiscountResponse;
import ecommerce.eco.model.response.CategoryLightningDealResponse;
import ecommerce.eco.model.response.CategoryResponse;
import ecommerce.eco.repository.CategoryRepository;
import ecommerce.eco.service.abstraction.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse create(CategoryRequest request) {
        if(contains(request.getDescription())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category already registered");
        }else {
            Category category = categoryMapper.entityToDto(request);
            Category categoryCreate = categoryRepository.save(category);
            return categoryMapper.dtoToEntityCreate(categoryCreate);
        }

    }

    private boolean contains(String description) {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().anyMatch(c->c.getDescription().equalsIgnoreCase(description));
    }

    @Override
    public void deleted(Long id) {
        Category category = getCategory(id);
        category.setSoftDeleted(true);
        categoryRepository.save(category);

    }

    @Override
    public CategoryResponse getCategoryAndProducts(Long id) {
        Category category = getCategory(id);
        return categoryMapper.dtoToEntity(category);
    }

    @Override
    public CategoryResponse findByDescription(String description) {
        Category category = categoryRepository.findByDescription(description);
        return categoryMapper.dtoToEntity(category);
    }

    @Override
    public CategoryDiscountResponse getCategoryWithDiscount(Long newOffersId) {
        Category category = getCategory(newOffersId);
        if(category.getId() != 5)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Categoria no encontrada prueba con id = 5");
        return categoryMapper.dtoToEntityWithDiscount(category);
    }

    @Override
    public Category findById(Long categoryId) {
        return getCategory(categoryId);
    }

    @Override
    public CategoryLightningDealResponse getCategoryLightningDeal(Long lightningDealId) {
        Category category = getCategory(lightningDealId);
        if(category.getId() != 6)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Categoria no encontrada prueba con id = 6");
        return categoryMapper.dtoToEntityLightningDeal(category);
    }


    private Category getCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty() || category.get().isSoftDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found or has been deleted");
        }
        return category.get();
    }
}
