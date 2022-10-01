package ecommerce.eco.controller;

import ecommerce.eco.model.request.CategoryRequest;
import ecommerce.eco.model.response.CategoryDiscountResponse;
import ecommerce.eco.model.response.CategoryResponse;
import ecommerce.eco.service.abstraction.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> create( @RequestBody @Valid CategoryRequest request){
        CategoryResponse response = categoryService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleted(@PathVariable Long id){
        categoryService.deleted(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryAndProducts(@PathVariable Long id){
        CategoryResponse response = categoryService.getCategoryAndProducts(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/description")
    public ResponseEntity<CategoryResponse> getByDescription(@RequestParam String description){
        CategoryResponse response = categoryService.findByDescription(description);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Endpoint Home devuelve los productos con descuento
    @GetMapping("/productWith/{newOffersId}")
    public ResponseEntity<CategoryDiscountResponse> getCategoryWithDiscount(@PathVariable Long newOffersId){
        CategoryDiscountResponse response = categoryService.getCategoryWithDiscount(newOffersId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }




}
