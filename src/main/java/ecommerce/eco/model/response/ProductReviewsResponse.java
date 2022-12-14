package ecommerce.eco.model.response;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductReviewsResponse {
    private List<ReviewResponse> reviewResponses;
}
