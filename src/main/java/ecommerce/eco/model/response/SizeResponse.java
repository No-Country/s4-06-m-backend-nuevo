package ecommerce.eco.model.response;

import lombok.*;

@Builder @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SizeResponse {
    private Long id;
    private String name;
    private String description;
}
