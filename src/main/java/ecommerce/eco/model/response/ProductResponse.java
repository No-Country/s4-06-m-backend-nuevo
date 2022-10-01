package ecommerce.eco.model.response;

import lombok.*;



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
