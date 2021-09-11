package cz.abdykili.eshop.controller;

import cz.abdykili.eshop.domain.OrderStatus;
import cz.abdykili.eshop.model.CartResponseDto;
import cz.abdykili.eshop.model.OrderRequestDto;
import cz.abdykili.eshop.model.OrderResponseDto;
import cz.abdykili.eshop.service.impl.CartServiceImp;
import cz.abdykili.eshop.service.impl.OrderServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderServiceImp orderService;

    @PostMapping
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.createOrder(orderRequestDto);
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable("id") Long id){
        orderService.deleteOrder(id);
    }

    @PutMapping("{id}/status/{status}")
    public OrderResponseDto changeStatus(@PathVariable Long id, @PathVariable OrderStatus status){
        return orderService.changeStatus(id, status);
    }

    @GetMapping
    public List<OrderResponseDto> getOrders(){
        return orderService.getOrders();
    }

}
