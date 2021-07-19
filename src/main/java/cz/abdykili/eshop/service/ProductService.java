package cz.abdykili.eshop.service;

import cz.abdykili.eshop.model.ProductResponseDto;
import cz.abdykili.eshop.model.ProductRequestDto;

import java.util.List;

public interface ProductService {
     List<ProductResponseDto> findAll();

     ProductResponseDto findProduct(Long id);

     ProductResponseDto saveProduct(ProductRequestDto productRequestDto);

     ProductResponseDto updateProduct(ProductRequestDto productRequestDto, Long id);

     void deleteProduct(Long id);
}
