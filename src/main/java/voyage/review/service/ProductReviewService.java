package voyage.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voyage.review.domain.Product;
import voyage.review.domain.ProductReview;
import voyage.review.dto.ProductReviewParam;
import voyage.review.repository.ProductRepository;
import voyage.review.repository.ProductReviewRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductReviewService {
    private final ProductRepository productRepository;
    private final ProductReviewRepository productReviewRepository;

    @Transactional
    public void addReview(Long productId, ProductReviewParam param) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        ProductReview productReview = new ProductReview(product, param);
        productReviewRepository.save(productReview);

        product.addReview(productReview);
    }

}
