package cz.abdykili.eshop.repository;

import cz.abdykili.eshop.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
