package ecommerce.eco.model.response;

import lombok.*;

import javax.persistence.Column;

@Builder @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ColorResponse {
    private Long id;
    private String name;
    private String description;
}
