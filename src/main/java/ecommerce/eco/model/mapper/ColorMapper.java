package ecommerce.eco.model.mapper;

import ecommerce.eco.model.entity.Color;
import ecommerce.eco.model.response.ColorResponse;
import ecommerce.eco.repository.ColorRepository;
import ecommerce.eco.service.abstraction.ColorService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class ColorMapper {
  @Autowired
  public  ColorRepository colorRepository;
  @Autowired
  public ColorService colorService;
  public ColorResponse entityToDto(Color color) {
    return ColorResponse.builder()
            .id(color.getId())
            .name(color.getName())
            .description(color.getDescription())
            .build();
  }
  public List<Color> dtoToEnty(List<String> request){
    List<Color> colors=new ArrayList<>();
      request.stream()
              .map(p->colors.add(colorService.findBy(p.toUpperCase())))
              .collect(Collectors.toList());
      return colors;
  }
}