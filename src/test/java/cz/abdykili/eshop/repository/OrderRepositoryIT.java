package cz.abdykili.eshop.repository;

import cz.abdykili.eshop.domain.CartEntity;
import cz.abdykili.eshop.domain.OrderEntity;
import cz.abdykili.eshop.domain.OrderStatus;
import cz.abdykili.eshop.domain.Product;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryIT implements WithAssertions {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void save(){
        Product product1 = new Product()
                .setName("product1");
        Product product2 = new Product()
                .setName("product2");

        Product persistedProduct1 = testEntityManager.persistAndFlush(product1);
        testEntityManager.detach(persistedProduct1);
        Optional<Product> productRepositoryById = productRepository.findById(persistedProduct1.getId());
        assertThat(productRepositoryById).isNotEmpty();
        Product retrievedProduct1 = productRepositoryById.get();

        Product persistedProduct2 = testEntityManager.persistAndFlush(product2);
        testEntityManager.detach(persistedProduct2);
        Optional<Product> productRepositoryById2 = productRepository.findById(persistedProduct2.getId());
        assertThat(productRepositoryById2).isNotEmpty();
        Product retrievedProduct2 = productRepositoryById2.get();

        List<Product> products = Arrays.asList(retrievedProduct1, retrievedProduct2);
        OrderEntity orderEntity = new OrderEntity()
                .setOrderStatus(OrderStatus.NEW)
                .setName("order")
                .setProducts(products);

        OrderEntity persistedOrder = testEntityManager.persistAndFlush(orderEntity);
        testEntityManager.detach(persistedOrder);
        Optional<OrderEntity> orderRepositoryById = orderRepository.findById(persistedOrder.getId());
        assertThat(orderRepositoryById).isNotEmpty();
        OrderEntity retrievedCart = orderRepositoryById.get();
        SoftAssertions.assertSoftly(softAssertions -> {
            assertThat(retrievedCart.getProducts().size()).isEqualTo(2);
            assertThat(retrievedCart.getProducts().get(0).getName()).isEqualTo(product1.getName());
            assertThat(retrievedCart.getProducts().get(1).getName()).isEqualTo(product2.getName());
        });
    }

}