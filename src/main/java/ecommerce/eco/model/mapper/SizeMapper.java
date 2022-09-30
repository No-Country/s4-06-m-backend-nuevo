package ecommerce.eco.model.mapper;

import ecommerce.eco.model.entity.Color;
import ecommerce.eco.model.entity.Size;
import ecommerce.eco.model.response.ColorResponse;
import ecommerce.eco.model.response.SizeResponse;
import org.springframework.stereotype.Component;

@Component
public class SizeMapper {
  public SizeResponse entityToDto(Size size) {
    return SizeResponse.builder()
            .id(size.getId())
            .name(size.getName())
            .description(size.getDescription())
            .build();
  }
}