package voyage.review.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "reviewCount")
    private Long reviewCount;

    @NotNull
    private Double score;

    @OneToMany(mappedBy = "product")
    private List<ProductReview> reviews = new ArrayList<>();

    public void updateStatsForNewReview(ProductReview review) {
        long newReviewCount = reviewCount + 1;
        double newAverageScore = (score / newReviewCount) * reviewCount + (review.getScore() / newReviewCount);
        this.reviewCount = newReviewCount;
        this.score = newAverageScore;
    }
}
