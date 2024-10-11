package voyage.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import voyage.review.dto.ProductReviewList;
import voyage.review.dto.ProductReviewParam;
import voyage.review.dto.ProductReviewQuery;
import voyage.review.service.DummyS3ImageService;
import voyage.review.service.ProductReviewService;

@RestController
@RequiredArgsConstructor
public class ProductReviewController {
    private final ProductReviewService productReviewService;
    private final DummyS3ImageService s3ImageService;

    @PostMapping("/products/{productId}/reviews")
    public void addReview(@PathVariable Long productId,
                          @Valid @RequestPart("review") ProductReviewParam reviewParam,
                          @RequestPart(value = "image", required = false) MultipartFile imageFile
    ) {
        Long reviewId = productReviewService.addReview(productId, reviewParam);
        String imageUrl = s3ImageService.uploadImage(imageFile);
        productReviewService.updateReviewImageUrl(reviewId, imageUrl);
    }

    @GetMapping("/products/{productId}/reviews")
    public ProductReviewList getReviews(@PathVariable Long productId,
                                        @Valid @ModelAttribute ProductReviewQuery query) {
        return productReviewService.getReviews(productId, query);
    }


}
