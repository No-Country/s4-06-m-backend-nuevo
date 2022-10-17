package ecommerce.eco.controller;

import ecommerce.eco.model.request.ProductRequest;
import ecommerce.eco.model.response.ProductResponse;
import ecommerce.eco.model.response.ProductReviewsResponse;
import ecommerce.eco.service.abstraction.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
@Api(value = "Product Controller", description = "Product functionalities")
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;

    @ApiOperation(value = "Registration of a product", notes = "Returns product created")
    @PostMapping("/add")
    public ResponseEntity<ProductResponse> upload(
            @RequestPart(value = "postimages", required = false) List<MultipartFile> postImage,
            @RequestPart(value = "product", required = true) ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.add(postImage, request));

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find by id ", notes = "Returns one Product")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        ProductResponse response = productService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Find by id and delete ", notes = "Returns http 204")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Find all products ", notes = "Returns list prdducts")
    public ResponseEntity<List<ProductResponse>> getAll() {
        List<ProductResponse> response = productService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @ApiOperation(value = "Filter method", notes = "Returns a list product and properties and description")
    @GetMapping("/filter/details")
    public List<ProductResponse> getDetailsByFilters(
            @RequestParam(required = false) String title,
            /*@RequestParam(required = false) Set<Long> genre,*/
            @RequestParam(required = false, defaultValue = "ASC") String order) {

        return productService.findByDetailsOrTitle(title, order);
    }

    @GetMapping("/review/{idProduct}")
    public ResponseEntity<ProductReviewsResponse> getReviewToProduct(@PathVariable Long idProduct){
        ProductReviewsResponse responses = productService.getByIdProduct(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

}
