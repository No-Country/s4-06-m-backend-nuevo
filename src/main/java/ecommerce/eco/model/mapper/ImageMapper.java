package ecommerce.eco.model.mapper;

import ecommerce.eco.model.entity.Image;
import ecommerce.eco.model.response.ImageResponse;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {
    public ImageResponse imageToDto(Image img){
        return ImageResponse.builder()
                .id(img.getId())
                .name(img.getFileName())
                .fileUrl(img.getImageUrl())
                .build();
    }
    public Image updateImageMapper(Image img, Image newImaga){
        img.setImageUrl(newImaga.getImageUrl());
        img.setFileName(newImaga.getFileName());
        return img;
    }

}