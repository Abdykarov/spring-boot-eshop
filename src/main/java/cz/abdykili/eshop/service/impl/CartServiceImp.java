package cz.abdykili.eshop.service.impl;

import cz.abdykili.eshop.domain.CartEntity;
import cz.abdykili.eshop.domain.Product;
import cz.abdykili.eshop.exception.ProductNotFoundException;
import cz.abdykili.eshop.mapperInterface.CartMapper;
import cz.abdykili.eshop.mapperInterface.ProductMapper;
import cz.abdykili.eshop.model.CartResponseDto;
import cz.abdykili.eshop.repository.CartRepository;
import cz.abdykili.eshop.repository.ProductRepository;
import cz.abdykili.eshop.service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CartServiceImp implements CartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final ProductMapper productMapper;
    private final CartMapper cartMapper;

    @Override
    @Transactional
    public CartResponseDto putProduct(Long cartId, Long productId) {
        final Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found", HttpStatus.NOT_FOUND));

        final CartEntity cartEntity = cartRepository.findById(cartId)
                .orElseThrow(() -> new ProductNotFoundException("Cart not found", HttpStatus.NOT_FOUND));

        cartEntity.getProducts().add(product);
        cartEntity.setModifiedAt(LocalDateTime.now());
        return cartMapper.toResponse(cartEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public CartResponseDto getCart(Long cartId) {
        final CartEntity cartEntity = cartRepository.findById(cartId)
                .orElseThrow(() -> new ProductNotFoundException("Cart not found", HttpStatus.NOT_FOUND));
        return cartMapper.toResponse(cartEntity);
    }

    @Override
    @Transactional
    public CartResponseDto createCart(Long productId) {
        final Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found", HttpStatus.NOT_FOUND));

        final CartEntity cartEntity = new CartEntity()
                .setProducts(List.of(product));

        CartEntity save = cartRepository.save(cartEntity);
        return cartMapper.toResponse(save);
    }
}
