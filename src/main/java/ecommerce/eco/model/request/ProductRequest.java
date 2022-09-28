package ecommerce.eco.model.request;

import ecommerce.eco.model.entity.Cart;
import ecommerce.eco.model.entity.Category;
import ecommerce.eco.model.entity.Image;
import ecommerce.eco.model.entity.Review;
import ecommerce.eco.model.enums.ColorEnum;
import ecommerce.eco.model.enums.SizeEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
