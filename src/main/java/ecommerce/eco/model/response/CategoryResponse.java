package ecommerce.eco.model.response;


import lombok.*;

import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryResponse {

    private Long id;
    private String description;

    //TODO falta devolver los productos
    private List<ProductResponse> products;

}
