package ecommerce.eco.controller;

import ecommerce.eco.model.request.ReviewRequest;
import ecommerce.eco.model.response.ReviewResponse;
import ecommerce.eco.service.abstraction.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/add")
    private ResponseEntity<ReviewResponse> add(@RequestBody @Valid ReviewRequest request){
        ReviewResponse response = reviewService.add(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
