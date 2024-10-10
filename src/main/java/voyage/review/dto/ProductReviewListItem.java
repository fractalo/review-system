package voyage.review.dto;

import voyage.review.domain.ProductReview;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public record ProductReviewListItem(
        Long id,
        Long userId,
        Long score,
        String content,
        String imageUrl,
        String createdAt
) {
    public ProductReviewListItem(ProductReview review) {
        this(
                review.getId(),
                review.getUserId(),
                Math.round(review.getScore()),
                review.getContent(),
                review.getImageUrl(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
                        .withZone(ZoneOffset.UTC)
                        .format(review.getCreatedAt())
        );
    }
}
