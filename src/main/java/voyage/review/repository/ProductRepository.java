package voyage.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voyage.review.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
