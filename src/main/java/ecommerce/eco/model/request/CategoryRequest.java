package ecommerce.eco.model.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryRequest {


    @NotEmpty(message = "description cannot be empty")
    private String description;

    private final boolean softDeleted = Boolean.FALSE;
}
