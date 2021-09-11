package cz.abdykili.eshop.controller;

import cz.abdykili.eshop.model.CartResponseDto;
import cz.abdykili.eshop.service.impl.CartServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
@AllArgsConstructor
public class CartController {

    private final CartServiceImp cartService;

    @PostMapping("/product/{id}")
    public CartResponseDto createCart(@PathVariable("id") Long productId){
        return cartService.createCart(productId);
    }

    @PutMapping("{cartId}/product/{productId}")
    public CartResponseDto putProduct(@PathVariable Long cartId, @PathVariable Long productId){
        return cartService.putProduct(cartId, productId);
    }

    @GetMapping("{cartId}")
    public CartResponseDto getCart(@PathVariable("cartId") Long cartId){
        return cartService.getCart(cartId);
    }

}
