package voyage.review.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record ProductReviewParam(
        @NotNull Long userId,
        @Range(min = 1, max = 5) @NotNull Double score,
        String content
) {}
