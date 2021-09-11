package cz.abdykili.eshop.service;

import cz.abdykili.eshop.domain.*;
import cz.abdykili.eshop.mapperInterface.OrderMapper;
import cz.abdykili.eshop.mapperInterface.ProductMapper;
import cz.abdykili.eshop.model.OrderRequestDto;
import cz.abdykili.eshop.model.OrderResponseDto;
import cz.abdykili.eshop.repository.CartRepository;
import cz.abdykili.eshop.repository.OrderRepository;
import cz.abdykili.eshop.service.impl.OrderServiceImp;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImpTest implements WithAssertions {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private OrderServiceImp orderServiceImp;

    @Test
    void getOrders() {
        //arrange
        final Integer supposedSize = 1;

        final OrderEntity orderEntity = new OrderEntity();
        final OrderResponseDto orderResponseDto = new OrderResponseDto();
        when(orderRepository.findAll()).thenReturn(Arrays.asList(
                orderEntity
        ));
        when(orderMapper.toResponse(orderEntity)).thenReturn(orderResponseDto);
        //act
        final List<OrderResponseDto> returnedList = orderServiceImp.getOrders();

        //assert
        verify(orderRepository).findAll();
        assertThat(supposedSize).isEqualTo(returnedList.size());
    }

    @Test
    void createOrder() {
        final OrderRequestDto orderRequestDto = new OrderRequestDto().setAddress("street").setName("order");
        final CartEntity cartEntity = new CartEntity().setProducts(Arrays.asList(new Product()));
        List<Product> products = cartEntity.getProducts();
        final OrderResponseDto orderResponseDto = new OrderResponseDto();
        OrderEntity orderEntity = new OrderEntity()
                .setOrderStatus(OrderStatus.NEW)
                .setName(orderRequestDto.getName())
                .setAddress(orderRequestDto.getAddress())
                .setProducts(new ArrayList<>(products));
        when(cartRepository.getById(orderRequestDto.getCartId())).thenReturn(cartEntity);
        when(orderRepository.save(orderEntity)).thenReturn(orderEntity);
        when(orderMapper.toResponse(orderEntity)).thenReturn(orderResponseDto);
        //act
        OrderResponseDto serviceImpOrder = orderServiceImp.createOrder(orderRequestDto);
        //assets
        verify(cartRepository).getById(orderRequestDto.getCartId());
        verify(orderRepository).save(orderEntity);
        verify(orderMapper).toResponse(orderEntity);
        assertThat(serviceImpOrder).isEqualTo(orderResponseDto);
    }

}