package ecommerce.eco.model.response;

import ecommerce.eco.model.entity.Size;
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
    private List<SizeResponse> sizes;
    private List<ColorResponse> colors;
    private String shortDetails;
    private String details;
    private String title;
    private double stars;
    private String category;
    private int view;
    private List<ImageResponse> imgList;
    //private List<ReviewResponse> reviewResponseList;

}
