package ecommerce.eco.model.mapper;

import ecommerce.eco.model.entity.Review;
import ecommerce.eco.model.request.ReviewRequest;
import ecommerce.eco.model.response.ReviewResponse;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class ReviewMapper {
    public Review entityToDto(ReviewRequest request) {
        return Review.builder()
                .score(request.getScore())
                .comment(request.getComment())
                .username(request.getUsername())
                .time(ZonedDateTime.now())
                .build();
    }

    public ReviewResponse dtoToEntity(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .comment(review.getComment())
                .score(review.getScore())
                .time(review.getTime())
                .username(review.getUsername())
                .build();
    }


    public void reviewUpdate(Review review, ReviewRequest request) {
        review.setComment(request.getComment());
        review.setScore(request.getScore());
        review.setUsername(request.getUsername());
        review.setTime(ZonedDateTime.now());

    }
}
