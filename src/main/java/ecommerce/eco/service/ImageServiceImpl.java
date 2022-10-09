package ecommerce.eco.service;

import ecommerce.eco.model.entity.Image;
import ecommerce.eco.model.mapper.ImageMapper;
import ecommerce.eco.repository.ImageRepository;
import ecommerce.eco.service.abstraction.AwsService;
import ecommerce.eco.service.abstraction.ImageService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final AwsService awsService;
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);
    @Override
    @Transactional
    public List<Image> imagesPost(List<MultipartFile> postImage) {
        List<Image> imagesPost=new ArrayList<>();
        for (MultipartFile m: postImage ) {
            imagesPost.add(imageRepository.save(awsService.uploadFile(m)));
        }
        LOGGER.warn("Array de amazon creado "+imagesPost.size());
        return imagesPost;
    }

    @Override
    @Transactional
    public Image imageUser(MultipartFile image) {
        Image img= awsService.uploadFile(image);
        LOGGER.warn("Iamegen creada"+ img.getFileName());
        return imageRepository.save(img);
    }

    public Image findById(Long id){
        return imageRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Image img = findById(id);
        imageRepository.delete(img);
    }
    @Override
    public Image update(Long id, Image newImage ){
        Image img = findById(id);
        awsService.deleteFileFromS3Bucket(img.getImageUrl());
        return imageMapper.updateImageMapper(img, newImage);
    }

    @Override
    public MultipartFile userDefault() throws IOException {
        String sCarpAct = System.getProperty("user.dir");
        InputStream inputStream = new FileInputStream(sCarpAct + "/src/main/resources/static/images/user.webp");
       return new MockMultipartFile("user.webp", "user.webp", "webp", inputStream);
    }


}
