package ecommerce.eco.service.abstraction;

import ecommerce.eco.model.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface AwsService {
    Image uploadFile(MultipartFile file);

    List<String> getObjectsFromS3();

    InputStream downloadFile(String key);
}
