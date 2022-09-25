package ecommerce.eco.service;

import ecommerce.eco.model.entity.Image;
import ecommerce.eco.service.abstraction.AwsService;
import ecommerce.eco.service.abstraction.ImageService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final AwsService awsService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);
    @Override
    @Transactional
    public List<Image> imagesPost(List<MultipartFile> postImage) {
        List<Image> imagesPost=new ArrayList<>();
        for (MultipartFile m: postImage ) {
            imagesPost.add(awsService.uploadFile(m));
        }
        LOGGER.warn("Array de amazon creado "+imagesPost.size());
        return imagesPost;
    }

    @Override
    @Transactional
    public Image imageUser(MultipartFile image) {
        return awsService.uploadFile(image);
    }
}
