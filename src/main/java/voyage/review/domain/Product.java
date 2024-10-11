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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long reviewCount;

    @NotNull
    @Column(name = "score")
    private Double averageScore;

    @OneToMany(mappedBy = "product")
    private List<ProductReview> reviews = new ArrayList<>();

    public void updateStatsForNewReview(ProductReview review) {
        long newReviewCount = reviewCount + 1;
        double newAverageScore = (averageScore / newReviewCount) * reviewCount + (review.getScore() / newReviewCount);
        this.reviewCount = newReviewCount;
        this.averageScore = newAverageScore;
    }
}
