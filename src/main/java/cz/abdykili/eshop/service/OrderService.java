package cz.abdykili.eshop.service;

import cz.abdykili.eshop.domain.OrderStatus;
import cz.abdykili.eshop.model.OrderRequestDto;
import cz.abdykili.eshop.model.OrderResponseDto;

import java.util.List;

/**
 * Order service interface
 */
public interface OrderService {

    /**
     * Method returns all orders from repository
     * @return
     */
    List<OrderResponseDto> getOrders();

    /**
     * Method creates new order
     * @param orderRequestDto
     * @return
     */
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);

    /**
     * Method changes a status of the existing order
     * @param orderId
     * @param status
     * @return
     */
    OrderResponseDto changeStatus(Long orderId, OrderStatus status);

    /**
     * Method deletes existing order by his id
     * @param id
     */
    void deleteOrder(Long id);
}
