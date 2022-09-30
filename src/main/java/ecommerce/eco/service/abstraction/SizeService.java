package ecommerce.eco.service.abstraction;

import ecommerce.eco.model.entity.Size;
import ecommerce.eco.model.response.SizeResponse;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface SizeService {
    Size findBy(String name);
    List<SizeResponse> findAll();
}
