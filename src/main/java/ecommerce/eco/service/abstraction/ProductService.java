package ecommerce.eco.service.abstraction;

import ecommerce.eco.model.request.ProductRequest;
import ecommerce.eco.model.response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ProductService {
    ProductResponse add(List<MultipartFile> postImage, ProductRequest request);
    ProductResponse getById(Long id);
    void delete(Long id);
    List<ProductResponse> getAll();
    List<ProductResponse> findByShortDetailsOrDetailsOrTitle(String shortDetails, String details, String title);
}
