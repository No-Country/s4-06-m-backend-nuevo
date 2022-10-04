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
        //User user = userService.getInfoUser(); //obtengo el user logueado
        if(request.getScore() < 0 || request.getScore() > 5)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El puntaje no puede ser mayor a 5 y menor a 0");
        Product product = productService.findById(request.getIdProduct());
        Review review = reviewMapper.entityToDto(request);
        request.setIdProduct(product.getId());
        /*  if(!user.getFullName().equalsIgnoreCase(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Your username is not that, try with: " + user.getFullName());
        }*/
        Review reviewCreate = reviewRepository.save(review);

        product.addReviews(reviewCreate);
        product.agregarEstrella(reviewCreate.getScore());
        productService.save(product);
        ReviewResponse response = reviewMapper.dtoToEntity(reviewCreate);
        response.setIdProduct(product.getId());
        return response;
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

    private Review getReview(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isEmpty() || review.get().isSoftDeleted()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review is not found");
        }
        return review.get();
    }
}
