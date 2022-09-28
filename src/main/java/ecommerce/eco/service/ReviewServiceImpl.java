package ecommerce.eco.service;

import ecommerce.eco.model.entity.User;
import ecommerce.eco.model.mapper.ReviewMapper;
import ecommerce.eco.model.request.ReviewRequest;
import ecommerce.eco.model.response.ReviewResponse;
import ecommerce.eco.repository.ReviewRepository;
import ecommerce.eco.service.abstraction.ReviewService;
import ecommerce.eco.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final UserService userService;
    @Override
    public ReviewResponse add(ReviewRequest request) {
        User user = userService.getInfoUser();//obtengo el user logueado
        

        return null;
    }
}
