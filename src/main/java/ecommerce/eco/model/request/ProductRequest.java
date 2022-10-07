package ecommerce.eco.model.request;


import ecommerce.eco.model.entity.Category;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {

    @NotEmpty(message = "description cannot be empty")
    private String shortDetails;
    @NotEmpty(message = "description cannot be empty")
    private String details;
    @NotEmpty(message = "description cannot be empty")
    private String title;
    @NotEmpty(message = "Brand cannot be empty")
    private String brand; // Marca
    private int view;
    private boolean stock;
    @NotNull(message = "You must specify the price")
    @Min(value = 0, message = "The minimum price is 0")
    private double price;
    private List<String> sizes;
    private List<String> colors;
    @NotNull(message = "id cannot by null")
    private Long categoryId;


}
