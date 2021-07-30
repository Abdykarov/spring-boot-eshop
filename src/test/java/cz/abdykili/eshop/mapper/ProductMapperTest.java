package cz.abdykili.eshop.mapper;

import cz.abdykili.eshop.domain.Product;
import cz.abdykili.eshop.model.ProductRequestDto;
import cz.abdykili.eshop.model.ProductResponseDto;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

class ProductMapperTest implements WithAssertions {

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void mapToProduct() {
        //arrange
        ProductRequestDto productRequestDto = new ProductRequestDto()
                .setDescription("descr")
                .setImage("image")
                .setPrice(BigDecimal.ONE)
                .setStock(1L)
                .setName("film");
        //act
        final Product mappedProduct = productMapper.mapToProduct(productRequestDto);

        //assert
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(mappedProduct.getDescription()).isEqualTo(productRequestDto.getDescription());
            softAssertions.assertThat(mappedProduct.getImage()).isEqualTo(productRequestDto.getImage());
            softAssertions.assertThat(mappedProduct.getPrice()).isEqualTo(productRequestDto.getPrice());
            softAssertions.assertThat(mappedProduct.getName()).isEqualTo(productRequestDto.getName());
            softAssertions.assertThat(mappedProduct.getStock()).isEqualTo(productRequestDto.getStock());
        });
    }

    @Test
    void mapToProduct_parameterIsNull(){
        //arrange
        ProductRequestDto productRequestDto = null;

        //act
        final Product mappedProduct = productMapper.mapToProduct(productRequestDto);

        //assert
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(mappedProduct).isNull();
            softAssertions.assertThat(productRequestDto).isEqualTo(mappedProduct);
        });
    }

    @Test
    void mapToResponse() {
        //arrange
        Product product = new Product()
                .setDescription("descr")
                .setImage("image")
                .setPrice(BigDecimal.ONE)
                .setStock(1L)
                .setName("film");
        //act
        final ProductResponseDto mappedResponse = productMapper.mapToResponse(product);

        //assert
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(mappedResponse.getDescription()).isEqualTo(product.getDescription());
            softAssertions.assertThat(mappedResponse.getImage()).isEqualTo(product.getImage());
            softAssertions.assertThat(mappedResponse.getPrice()).isEqualTo(product.getPrice());
            softAssertions.assertThat(mappedResponse.getName()).isEqualTo(product.getName());
            softAssertions.assertThat(mappedResponse.getStock()).isEqualTo(product.getStock());
        });
    }

    @Test
    void mapToResponse_parameterIsNull(){
        //arrange
        Product product = null;

        //act
        final ProductResponseDto mappedResponse = productMapper.mapToResponse(product);

        //assert
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(mappedResponse).isNull();
            softAssertions.assertThat(mappedResponse).isEqualTo(product);
        });
    }
}