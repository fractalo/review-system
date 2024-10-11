package voyage.review;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import voyage.review.domain.Product;
import voyage.review.repository.ProductRepository;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final ProductRepository productRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initProducts() {
        for (int i = 0; i < 10; ++i) {
            productRepository.save(Product.createProduct());
        }
    }
}
