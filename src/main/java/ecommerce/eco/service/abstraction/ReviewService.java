package ecommerce.eco.service.abstraction;

import ecommerce.eco.model.request.ReviewRequest;
import ecommerce.eco.model.response.ReviewResponse;

import java.util.List;

public interface ReviewService {
    ReviewResponse add(ReviewRequest request);

    ReviewResponse update(Long id, ReviewRequest request);

    ReviewResponse getById(Long id);

    List<ReviewResponse> getAll();
}
