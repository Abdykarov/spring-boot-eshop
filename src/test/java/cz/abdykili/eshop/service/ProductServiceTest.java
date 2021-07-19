package cz.abdykili.eshop.service;

import cz.abdykili.eshop.domain.Product;
import cz.abdykili.eshop.model.ProductResponseDto;
import cz.abdykili.eshop.repository.ProductRepository;
import cz.abdykili.eshop.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;


class ProductServiceTest {
    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private ProductServiceImpl productService = new ProductServiceImpl(productRepository);

    @Test
    public void findAll_TestArraySize() {
        //arrange
        Integer supposedSize = 3;
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();

        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(
                product1,
                product2,
                product3
        ));

        //act
        List<ProductResponseDto> returnedList = productService.findAll();

        //assert
        assertEquals(supposedSize, returnedList.size());

    }

    @Test
    void findProduct_TestIdOfTheFoundProduct() {
        //arrange
        Product testProduct = new Product();
        testProduct.setId(1L);
        Mockito.when(productRepository.findById(anyLong())).thenReturn(Optional.of(testProduct));


        //act
        ProductResponseDto returnedProduct = productService.findProduct(2L);
        Long returnProductId = returnedProduct.getId();

        //assert
        assertEquals(1, returnProductId);
    }

    @Test
    public void deleteProduct_TestSizeOfArray(){
        // TODO Create a deleteMethod test
    }

    @Test
    public void updateProduct_TestChangedName(){
        // TODO Create a updateProudct test
    }
}

