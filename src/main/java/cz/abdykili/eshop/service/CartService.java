package cz.abdykili.eshop.service;

import cz.abdykili.eshop.model.CartResponseDto;

import java.util.List;

/**
 * Cart service interface
 */
public interface CartService {

    /**
     * Method adds existing product to the cart
     * @param cartId
     * @param productId
     * @return
     */
    CartResponseDto putProduct(Long cartId, Long productId);

    /**
     * Method returns a cart with defined id
     * @param cartId
     * @return
     */
    CartResponseDto getCart(Long cartId);

    /**
     * Method creates new cart by adding first product to new cart
     * @param productId
     * @return
     */
    CartResponseDto createCart(Long productId);
}
