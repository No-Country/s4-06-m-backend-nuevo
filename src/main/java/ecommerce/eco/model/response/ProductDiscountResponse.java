package ecommerce.eco.model.response;


import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDiscountResponse {

    private Long id;
    private boolean stock;
    private double priceWithoutDiscount; // sin descuento
    private double priceWithDiscount;// con descuento
    private int discount; // descuento
    private String brand; // Marca
    private String shortDetails;
    private String details;
    private String title;
    private String category;
    private List<ImageResponse> imgList;
   // private List<ReviewResponse> reviewResponses;

}
