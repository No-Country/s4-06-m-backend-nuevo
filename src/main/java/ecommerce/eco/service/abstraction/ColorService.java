package ecommerce.eco.service.abstraction;

import ecommerce.eco.model.entity.Color;
import ecommerce.eco.model.response.ColorResponse;

import java.util.List;


public interface ColorService {
    Color findBy(String name);
    boolean checkList(List<String> colors);
    List<ColorResponse> findAll();
    List<Color> stringToEnty(List<String> request);
}
