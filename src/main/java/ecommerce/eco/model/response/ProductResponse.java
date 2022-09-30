package ecommerce.eco.model.response;

import ecommerce.eco.model.enums.ColorEnum;
import ecommerce.eco.model.enums.SexEnum;
import ecommerce.eco.model.enums.SizeEnum;
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
public class ProductResponse {

    private Long id;
    private boolean stock;
    private double price;
    private String brand; // Marca
    private String size;
    private String color;
    private String shortDetails;
    private String details;
    private String title;
    private List<ImageResponse> imgList;
}
