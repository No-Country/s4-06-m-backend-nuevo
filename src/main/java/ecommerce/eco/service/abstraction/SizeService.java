package ecommerce.eco.service.abstraction;

import ecommerce.eco.model.entity.Size;
import ecommerce.eco.model.response.SizeResponse;

import java.util.List;

public interface SizeService {
    Size findBy(String name);
   // boolean checkList(List<String> sizes);
    List<SizeResponse> findAll();
    List<Size> stringToEnty(List<String> sizes);
}
