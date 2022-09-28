package ecommerce.eco.controller;

import ecommerce.eco.model.request.ProductRequest;
import ecommerce.eco.model.response.ProductResponse;
import ecommerce.eco.service.abstraction.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @ApiOperation(value = "Registration of a product", notes = "Returns product created" )
    @PostMapping("/add")

    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse upload(
            @RequestPart(value="postimages",required=false) List<MultipartFile> postImage,
            @RequestPart (value="product", required=true) ProductRequest request){
        return productService.add(postImage,request);

    }
}
