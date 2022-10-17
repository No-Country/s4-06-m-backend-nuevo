package ecommerce.eco.service;

import ecommerce.eco.model.entity.Product;
import ecommerce.eco.model.entity.Review;
import ecommerce.eco.model.entity.User;
import ecommerce.eco.model.mapper.ReviewMapper;
import ecommerce.eco.model.request.ReviewRequest;
import ecommerce.eco.model.response.ReviewResponse;
import ecommerce.eco.repository.ReviewRepository;
import ecommerce.eco.service.abstraction.ProductService;
import ecommerce.eco.service.abstraction.ReviewService;
import ecommerce.eco.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final ProductService productService;

    private final UserService userService;
    @Override
    public ReviewResponse add(ReviewRequest request) {
        User user = userService.getInfoUser(); //obtengo el user logueado
        Product product = productService.findById(request.getIdProduct());
        if(product.isSoftDeleted()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found");
        }
        Review review = reviewMapper.entityToDto(request);
       // request.setIdProduct(product.getId());
        review.setUsername(user.getUsername());
        Review reviewCreate = reviewRepository.save(review);

        product.addReviews(reviewCreate);
        product.addStar(reviewCreate.getScore());
        productService.save(product);
        return reviewMapper.dtoToEntity(reviewCreate);
    }

    @Override
    public ReviewResponse update(Long id, ReviewRequest request) {

        Optional<Review> review = reviewRepository.findById(id);
        if(review.isEmpty() || review.get().isSoftDeleted()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review is not found");
        }
        reviewMapper.reviewUpdate(review.get(), request);
        Review reviewCreate = reviewRepository.save(review.get());
        return reviewMapper.dtoToEntity(reviewCreate);
    }

    @Override
    public ReviewResponse getById(Long id) {
        Review review = getReview(id);
        return reviewMapper.dtoToEntity(review);
    }

    @Override
    public List<ReviewResponse> getAll() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewResponse> responses = reviewMapper.dtoToEntityList(reviews);
        return responses;
    }

    private Review getReview(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isEmpty() || review.get().isSoftDeleted()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "review not found");
        }
        return review.get();
    }


}
