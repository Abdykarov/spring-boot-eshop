package cz.abdykili.eshop.repository;

import cz.abdykili.eshop.domain.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

}
