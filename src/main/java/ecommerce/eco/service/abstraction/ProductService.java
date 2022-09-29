package ecommerce.eco.service.abstraction;

import ecommerce.eco.model.entity.Product;
import ecommerce.eco.model.request.ProductRequest;
import ecommerce.eco.model.response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ProductService {
    ProductResponse add(List<MultipartFile> postImage, ProductRequest request);

    Product findById(Long idProduct);

    void save(Product product);
}
