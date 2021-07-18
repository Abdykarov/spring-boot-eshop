package cz.abdykili.eshop.repository;

import cz.abdykili.eshop.domain.Product;
import cz.abdykili.eshop.payload.ProductResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<ProductResponseDto> findProductById(Long id);
}
