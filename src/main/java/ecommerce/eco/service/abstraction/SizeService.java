package ecommerce.eco.service.abstraction;

import ecommerce.eco.model.entity.Size;

public interface SizeService {
    Size findBy(String name);
}
