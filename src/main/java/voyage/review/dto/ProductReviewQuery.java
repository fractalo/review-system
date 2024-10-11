package voyage.review.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ProductReviewQuery {
    private Long cursor;

    @PositiveOrZero
    private Long size = 10L;
}
