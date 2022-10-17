package ecommerce.eco.controller;

import ecommerce.eco.model.request.ReviewRequest;
import ecommerce.eco.model.response.ProductReviewsResponse;
import ecommerce.eco.model.response.ReviewResponse;
import ecommerce.eco.service.abstraction.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @PutMapping("/{id}")
    private ResponseEntity<ReviewResponse> update(@PathVariable Long id, @RequestBody @Valid ReviewRequest request){
        ReviewResponse response = reviewService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{idReview}")
    public ResponseEntity<ReviewResponse> getById(@PathVariable Long idReview){
        ReviewResponse response = reviewService.getById(idReview);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReviewResponse>> getAll(){
        List<ReviewResponse> responses = reviewService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }


}
