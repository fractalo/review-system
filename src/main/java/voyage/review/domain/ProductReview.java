package voyage.review.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;
import voyage.review.dto.ProductReviewParam;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(name = "uniqueReviewPerProductAndUser", columnList = "user_id, product_id", unique = true)
})
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_review_id")
    private Long id;

    @NotNull
    private Long userId;

    @Range(min = 1, max = 5)
    @NotNull
    private Double score;

    private String content;

    @Setter
    private String imageUrl;

    @CreationTimestamp
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductReview(Product product, ProductReviewParam param) {
        this.product = product;
        this.userId = param.userId();
        this.score = param.score();
        this.content = param.content();
    }

}
