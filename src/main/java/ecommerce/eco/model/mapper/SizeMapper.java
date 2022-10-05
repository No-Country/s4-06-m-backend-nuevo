package ecommerce.eco.model.mapper;

import ecommerce.eco.model.entity.Color;
import ecommerce.eco.model.entity.Size;
import ecommerce.eco.model.response.ColorResponse;
import ecommerce.eco.model.response.SizeResponse;
import ecommerce.eco.service.abstraction.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SizeMapper {
  @Autowired
  private SizeService sizeService;
  public SizeResponse entityToDto(Size size) {
    return SizeResponse.builder()
            .id(size.getId())
            .name(size.getName())
            .description(size.getDescription())
            .build();
  }
  public List<Size> dtoToEnty(List<String> request){
    List<Size> colors=new ArrayList<>();
    request.stream()
            .map(p->colors.add(sizeService.findBy(p.toUpperCase())))
            .collect(Collectors.toList());
    return colors;
  }
}