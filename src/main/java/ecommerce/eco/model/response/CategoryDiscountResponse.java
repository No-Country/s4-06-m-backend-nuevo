package ecommerce.eco.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDiscountResponse {

    private Long id;
    private String description;
    //TODO  devuelve los productos con descuento
    private List<ProductDiscountResponse> products;

}
