package ecommerce.eco.model.response;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryLightningDealResponse {
    private Long id;
    private String description;
    private List<ProductLightningDealResponse> lightningDealResponseList;
}
