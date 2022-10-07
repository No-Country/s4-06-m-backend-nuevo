package ecommerce.eco.model.mapper;

import ecommerce.eco.model.entity.Color;
import ecommerce.eco.model.response.ColorResponse;
import org.springframework.stereotype.Component;

;

@Component
public class ColorMapper {
  public ColorResponse entityToDto(Color color) {
    return ColorResponse.builder()
            .id(color.getId())
            .name(color.getName())
            .description(color.getDescription())
            .build();
  }

}