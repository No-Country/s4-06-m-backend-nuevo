package ecommerce.eco.service.abstraction;

import ecommerce.eco.model.entity.Color;
import ecommerce.eco.model.response.ColorResponse;

import java.util.List;


public interface ColorService {
    public Color findBy(String name);
    public Boolean checkListColor(List<String> colors);
    public List<ColorResponse> findAll();
    public List<Color> stringToEnty(List<String> request);
}
