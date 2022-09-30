package ecommerce.eco.model.request;


import lombok.*;

import javax.validation.constraints.NotEmpty;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {

    @NotEmpty(message = "description cannot be empty")
    private String shortDetails;
    private String details;
    private String title;
}
