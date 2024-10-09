package voyage.review.dto;

import java.util.List;

public record ProductReviewList(
        Long totalCount,
        Double score,
        Long cursor,
        List<ProductReviewListItem> reviews
) {}
