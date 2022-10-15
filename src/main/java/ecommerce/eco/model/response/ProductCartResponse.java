package ecommerce.eco.model.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCartResponse {

    private Long id;
    private String details;
    private double price;

}
