package voyage.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voyage.review.domain.ProductReview;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
}
