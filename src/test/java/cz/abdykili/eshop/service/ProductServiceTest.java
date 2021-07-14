package cz.abdykili.eshop.service;

import cz.abdykili.eshop.model.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;  

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
    }

    @Test
    void findAll() {
        //arrange
        Integer supposedSize = 3;

        //act
        List<ProductDto> returnedList = productService.findAll();

        //assert
        assertEquals(supposedSize,returnedList.size());

    }

    @Test
    void findProduct() {
        //arrange
        ProductDto testProduct = new ProductDto();
        testProduct.setId(1L);

        //act
        ProductDto returnedProduct = productService.findProduct(1);
        Long returnProductId = returnedProduct.getId();

        //assert
        assertEquals(testProduct.getId(), returnProductId);
    }
}

