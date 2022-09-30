package ecommerce.eco.model.request;

import ecommerce.eco.model.entity.Category;
import ecommerce.eco.model.enums.ColorEnum;
import ecommerce.eco.model.enums.SizeEnum;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


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
    private String view;
    private boolean stock;
    @NotNull(message = "You must specify the price")
    @Min(value = 0, message = "The minimum price is 0")
    private double price;
    private String size;
    private String color;
    private Category category;

}
