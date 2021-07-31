package cz.abdykili.eshop.service;

import cz.abdykili.eshop.domain.Product;
import cz.abdykili.eshop.exception.ProductNotFoundException;
import cz.abdykili.eshop.mapper.ProductMapper;
import cz.abdykili.eshop.model.ProductRequestDto;
import cz.abdykili.eshop.model.ProductResponseDto;
import cz.abdykili.eshop.repository.ProductRepository;
import cz.abdykili.eshop.service.impl.ProductServiceImpl;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest implements WithAssertions {

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void findAll_TestArraySize() {
        //arrange
        final Integer supposedSize = 3;

        when(productRepository.findAll()).thenReturn(Arrays.asList(
                new Product(),
                new Product(),
                new Product()
        ));

        //act
        final List<ProductResponseDto> returnedList = productService.findAll();

        //assert
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(supposedSize).isEqualTo(returnedList.size());
        });

    }

    @Test
    void findProduct_TestIdOfTheFoundProduct() {
        //arrange
        final Product testProduct = new Product()
                .setId(2L)
                .setDescription("desc")
                .setImage("image")
                .setName("name")
                .setStock(2L)
                .setPrice(BigDecimal.ONE);

        final ProductResponseDto productResponseDto = new ProductResponseDto();

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(testProduct));

        when(productMapper.mapToResponse(testProduct)).thenReturn(productResponseDto
                .setId(testProduct.getId()));

        //act
        final ProductResponseDto returnedProduct = productService.findProduct(2L);

        //assert
        assertNotNull(returnedProduct);
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(2L).isEqualTo(returnedProduct.getId());
        });
    }

    @Test
    public void findProduct_ProductNotFoundException(){
        //arrange
        when(productRepository.findById(1L))
                .thenReturn(Optional.empty());

        //act
        final ProductNotFoundException productNotFoundException = assertThrows(ProductNotFoundException.class,
                () -> productService.findProduct(1L));

        //assert
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(productNotFoundException.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
            softAssertions.assertThat(productNotFoundException.getMessage()).isEqualTo("Product with id 1 not found!");
        });
    }

    @Test
    public void deleteProduct_TestTimes(){
        //arrange
        when(productRepository.existsById(1L)).thenReturn(true);

        //act
        productService.deleteProduct(1L);

        //assert
        verify(productRepository, times(1)).deleteById(eq(1L));
    }

    @Test
    public void deleteProduct_NotFound(){
        //arrange
        when(productRepository.existsById(1L)).thenReturn(false);

        //act
        final ProductNotFoundException productNotFoundException = assertThrows(ProductNotFoundException.class,
                () -> productService.deleteProduct(1L));

        //assert
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(productNotFoundException.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
            softAssertions.assertThat(productNotFoundException.getMessage()).isEqualTo("Product was not found with id 1");
        });
    }

    @Test
    public void updateProduct_TestChangedName(){
        //arrange
        final Long id = 1L;
        final ProductRequestDto productRequestDto = new ProductRequestDto()
                .setName("newName");

        final Product product = new Product()
                .setName("actualName");

        final ProductResponseDto productResponseDto = new ProductResponseDto()
                .setName(product.getName())
                .setImage(product.getImage());

        when(productRepository.existsById(1L)).thenReturn(true);
        when(productMapper.mapToProduct(productRequestDto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product.setName(productRequestDto.getName()));
        when(productMapper.mapToResponse(product)).thenReturn(productResponseDto);

        //act
        final ProductResponseDto productResponseDtoReturned = productService.updateProduct(productRequestDto,id);

        //assert
        verify(productRepository, times(1)).save(product);
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(productRequestDto.getName()).isNotEqualTo(productResponseDtoReturned.getName());
        });
    }

    @Test
    public void updateProduct_ProductNotFound(){
        //arrange
        final Long id = 1L;
        final ProductRequestDto productRequestDto = new ProductRequestDto()
                .setName("newName");
        when(productRepository.existsById(1L)).thenReturn(false);

        //act
        final ProductNotFoundException productNotFoundException = assertThrows(ProductNotFoundException.class,
                () -> productService.updateProduct(productRequestDto, id));

        //assert
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(productNotFoundException.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
            softAssertions.assertThat(productNotFoundException.getMessage()).isEqualTo("Product was not found with id 1");
        });
    }

    @Test
    public void saveProduct(){
        //arrange
        final ProductRequestDto productRequestDto = new ProductRequestDto()
                .setName("name")
                .setImage("image");

        final Product product = new Product()
                .setName(productRequestDto.getName())
                .setImage(productRequestDto.getImage());

        final ProductResponseDto productResponseDto = new ProductResponseDto()
                .setName(product.getName())
                .setImage(product.getImage());

        when(productMapper.mapToProduct(productRequestDto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.mapToResponse(product)).thenReturn(productResponseDto);

        //act
        final ProductResponseDto productResponseDtoReturned = productService.saveProduct(productRequestDto);

        //assert
        verify(productRepository, times(1)).save(product);
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(productRequestDto.getName()).isEqualTo(productResponseDtoReturned.getName());
            softAssertions.assertThat(productRequestDto.getImage()).isEqualTo(productResponseDtoReturned.getImage());
        });


    }
}

