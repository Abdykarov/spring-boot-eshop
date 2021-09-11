package cz.abdykili.eshop.service.impl;

import cz.abdykili.eshop.domain.CartEntity;
import cz.abdykili.eshop.domain.OrderEntity;
import cz.abdykili.eshop.domain.OrderStatus;
import cz.abdykili.eshop.domain.Product;
import cz.abdykili.eshop.exception.ProductNotFoundException;
import cz.abdykili.eshop.mapperInterface.OrderMapper;
import cz.abdykili.eshop.mapperInterface.ProductMapper;
import cz.abdykili.eshop.model.OrderRequestDto;
import cz.abdykili.eshop.model.OrderResponseDto;
import cz.abdykili.eshop.repository.CartRepository;
import cz.abdykili.eshop.repository.OrderRepository;
import cz.abdykili.eshop.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;
    private final CartRepository cartRepository;

    @Override
    public List<OrderResponseDto> getOrders() {
        List<OrderEntity> all = orderRepository.findAll();

        return all.stream()
                .map(orderEntity -> orderMapper.toResponse(orderEntity))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        final CartEntity cart = cartRepository.getById(orderRequestDto.getCartId());
        List<Product> products = cart.getProducts();
        OrderEntity orderEntity = new OrderEntity()
                .setOrderStatus(OrderStatus.NEW)
                .setName(orderRequestDto.getName())
                .setAddress(orderRequestDto.getAddress())
                .setProducts(new ArrayList<>(products));
        OrderEntity save = orderRepository.save(orderEntity);
        log.info("Creating order | Deleting existing cart");
        cartRepository.deleteById(orderRequestDto.getCartId());
        return orderMapper.toResponse(save);
    }

    @Override
    @Transactional
    public OrderResponseDto changeStatus(Long orderId, OrderStatus status) {
        final OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(()-> new ProductNotFoundException("Order not found", HttpStatus.NOT_FOUND));
        if (status != null){
            orderEntity.setOrderStatus(status);
        }
        return orderMapper.toResponse(orderEntity);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
