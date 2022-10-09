package ecommerce.eco.model.response;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductLightningDealResponse{

    private Long id;
    private double price;
    private String details;
    private List<ImageResponse> imgList;



}
