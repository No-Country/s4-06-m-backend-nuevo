package ecommerce.eco.service.abstraction;

import ecommerce.eco.model.entity.Color;
import ecommerce.eco.model.response.ColorResponse;

import java.util.List;


public interface ColorService {
    Color findBy(String name);
    List<ColorResponse> findAll();
}
