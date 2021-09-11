package cz.abdykili.eshop.repository;

import cz.abdykili.eshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
