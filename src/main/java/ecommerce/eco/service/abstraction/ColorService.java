package ecommerce.eco.service.abstraction;

import ecommerce.eco.model.entity.Color;


public interface ColorService {
    Color findBy(String name);
}
