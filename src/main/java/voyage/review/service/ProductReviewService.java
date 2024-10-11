package voyage.review.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voyage.review.domain.Product;
import voyage.review.domain.ProductReview;
import voyage.review.domain.QProductReview;
import voyage.review.dto.ProductReviewList;
import voyage.review.dto.ProductReviewListItem;
import voyage.review.dto.ProductReviewParam;
import voyage.review.dto.ProductReviewQuery;
import voyage.review.repository.ProductRepository;
import voyage.review.repository.ProductReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductReviewService {
    private final ProductRepository productRepository;
    private final ProductReviewRepository productReviewRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Transactional
    public Long addReview(Long productId, ProductReviewParam param) {
        Product product = productRepository.findByIdWithLock(productId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        ProductReview productReview = new ProductReview(product, param);
        product.updateStatsForNewReview(productReview);
        productReviewRepository.save(productReview);

        return productReview.getId();
    }

    @Transactional(readOnly = true)
    public ProductReviewList getReviews(Long productId, ProductReviewQuery query) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        QProductReview productReview = QProductReview.productReview;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(productReview.product.eq(product));

        if (query.getCursor() != null) {
            builder.and(productReview.id.lt(query.getCursor()));
        }

        List<ProductReviewListItem> reviews = jpaQueryFactory
                .selectFrom(productReview)
                .where(builder)
                .orderBy(productReview.id.desc())
                .limit(query.getSize())
                .fetch()
                .stream()
                .map(ProductReviewListItem::new)
                .toList();

        Long nextCursor = null;
        if (!reviews.isEmpty()) {
            nextCursor = reviews.get(reviews.size() - 1).id();
        }

        return new ProductReviewList(
                product.getReviewCount(),
                product.getAverageScore(),
                nextCursor,
                reviews
        );
    }

    @Transactional
    public void updateReviewImageUrl(Long reviewId, String imageUrl) {
        ProductReview review = productReviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품 리뷰입니다."));
        review.setImageUrl(imageUrl);
    }

}
