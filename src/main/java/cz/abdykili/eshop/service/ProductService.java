package cz.abdykili.eshop.service;

import cz.abdykili.eshop.payload.ProductRequestDto;
import cz.abdykili.eshop.payload.ProductResponseDto;

import java.util.List;

public interface ProductService {
     List<ProductResponseDto> findAll();

     ProductResponseDto findProduct(Long id);

     ProductResponseDto saveProduct(ProductRequestDto productRequestDto);
}
