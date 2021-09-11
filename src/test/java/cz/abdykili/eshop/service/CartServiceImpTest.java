package cz.abdykili.eshop.service;

import cz.abdykili.eshop.domain.CartEntity;
import cz.abdykili.eshop.domain.Product;
import cz.abdykili.eshop.mapperInterface.CartMapper;
import cz.abdykili.eshop.mapperInterface.ProductMapper;
import cz.abdykili.eshop.model.CartResponseDto;
import cz.abdykili.eshop.repository.CartRepository;
import cz.abdykili.eshop.repository.ProductRepository;
import cz.abdykili.eshop.service.impl.CartServiceImp;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceImpTest implements WithAssertions {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private CartMapper cartMapper;

    @InjectMocks
    private CartServiceImp cartServiceImp;

//    @Test
//    void putProduct() {
//        //assert
//        final Product product = new Product().setName("Book");
//        final CartEntity cartEntity = new CartEntity().setProducts(Arrays.asList(new Product().setName("Book2")));
//        final CartResponseDto cartResponseDto = new CartResponseDto();
//        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product));
//        when(cartRepository.findById(2L)).thenReturn(Optional.ofNullable(cartEntity));
//        when(cartMapper.toResponse(cartEntity)).thenReturn(cartResponseDto);
//
//        //act
//        CartResponseDto cart = cartServiceImp.putProduct(2L, 1L);
//
//        //assert
//        verify(productRepository).findById(1L);
//        verify(cartRepository).findById(2L);
//        verify(cartMapper).toResponse(cartEntity);
//        assertThat(cart).isEqualTo(cartResponseDto);
//
//    }

    @Test
    void getCart() {
        final CartEntity cart = new CartEntity();
        final CartResponseDto cartResponseDto = new CartResponseDto();
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        when(cartMapper.toResponse(cart)).thenReturn(cartResponseDto);
        //act
        CartResponseDto cartImp = cartServiceImp.getCart(1L);
        //assert
        verify(cartRepository).findById(1L);
        verify(cartMapper).toResponse(cart);
        assertThat(cartImp).isEqualTo(cartResponseDto);
    }

    @Test
    void createCart() {
        final Product product = new Product();
        final CartResponseDto cartResponseDto = new CartResponseDto();
        final CartEntity cartEntity = new CartEntity()
                .setProducts(List.of(product));
        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product));
        when(cartRepository.save(cartEntity)).thenReturn(cartEntity);
        when(cartMapper.toResponse(cartEntity)).thenReturn(cartResponseDto);
        //act
        CartResponseDto cart = cartServiceImp.createCart(1L);
        //assert
        verify(productRepository).findById(1L);
        verify(cartRepository).save(cartEntity);
        verify(cartMapper).toResponse(cartEntity);
        assertThat(cart).isEqualTo(cartResponseDto);
    }
}