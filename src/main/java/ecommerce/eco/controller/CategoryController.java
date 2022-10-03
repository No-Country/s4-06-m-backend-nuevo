package ecommerce.eco.controller;

import ecommerce.eco.model.request.CategoryRequest;
import ecommerce.eco.model.response.CategoryDiscountResponse;
import ecommerce.eco.model.response.CategoryLightningDealResponse;
import ecommerce.eco.model.response.CategoryResponse;
import ecommerce.eco.service.abstraction.CategoryService;

import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "create category", notes = "return category" )
    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> create( @RequestBody @Valid CategoryRequest request){
        CategoryResponse response = categoryService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "deleted", notes = "deleted category" )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleted(@PathVariable Long id){
        categoryService.deleted(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @ApiOperation(value = "get by id", notes = "returns category by name and its products" )
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryAndProducts(@PathVariable Long id){
        CategoryResponse response = categoryService.getCategoryAndProducts(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @ApiOperation(value = "get by description", notes = "returns category by name and its products" )
    @GetMapping("/description")
    public ResponseEntity<CategoryResponse> getByDescription(@RequestParam String description){
        CategoryResponse response = categoryService.findByDescription(description);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Endpoint Home devuelve los productos con descuento
    @ApiOperation(value = "get new offers", notes = "Returns new offers" )
    @GetMapping("/productWith/{newOffersId}")
    public ResponseEntity<CategoryDiscountResponse> getCategoryWithDiscount(@PathVariable Long newOffersId){
        CategoryDiscountResponse response = categoryService.getCategoryWithDiscount(newOffersId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    //Endpoint Home devuelve los productos con ofertas relampago
    @ApiOperation(value = "get flash deals", notes = "Returns lash deals" )
    @GetMapping("/product/{lightningDealId}")
    public ResponseEntity<CategoryLightningDealResponse> getCategoryLightningDeal(@PathVariable Long lightningDealId){
        CategoryLightningDealResponse response = categoryService.getCategoryLightningDeal(lightningDealId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
