package ecommerce.eco.service;

import ecommerce.eco.model.entity.Product;
import ecommerce.eco.model.entity.Review;
import ecommerce.eco.model.entity.User;
import ecommerce.eco.model.mapper.ReviewMapper;
import ecommerce.eco.model.request.ReviewRequest;
import ecommerce.eco.model.response.ReviewResponse;
import ecommerce.eco.repository.ProductRepository;
import ecommerce.eco.repository.ReviewRepository;
import ecommerce.eco.service.abstraction.ProductService;
import ecommerce.eco.service.abstraction.ReviewService;
import ecommerce.eco.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final UserService userService;
    @Override
    public ReviewResponse add(ReviewRequest request) {
        User user = userService.getInfoUser(); //obtengo el user logueado
        Product product = productService.findById(request.getIdProduct());
        Review review = reviewMapper.entityToDto(request);
        request.setIdProduct(product.getId());
        if(!user.getFullName().equalsIgnoreCase(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tu nombre de usuario no es ese, prueba con: "
            + user.getFullName());
        }
        Review reviewCreate = reviewRepository.save(review);
        product.getReviews().add(reviewCreate);
        productRepository.save(product);
        ReviewResponse response = reviewMapper.dtoToEntity(reviewCreate);
        response.setIdProduct(product.getId());
        return response;
    }
}
