package ecommerce.eco.service.abstraction;

import ecommerce.eco.model.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    List<Image> imagesPost(List<MultipartFile> postImagep);
    Image imageUser(MultipartFile image);
}
