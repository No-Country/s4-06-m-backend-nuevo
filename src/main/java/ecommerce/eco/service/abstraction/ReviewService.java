package ecommerce.eco.service.abstraction;

import ecommerce.eco.model.request.ReviewRequest;
import ecommerce.eco.model.response.ReviewResponse;

public interface ReviewService {
    ReviewResponse add(ReviewRequest request);

    ReviewResponse update(Long id, ReviewRequest request);
}
