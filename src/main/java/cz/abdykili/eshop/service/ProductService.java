package cz.abdykili.eshop.service;

import cz.abdykili.eshop.model.ProductDto;
import cz.abdykili.eshop.payload.ProductRequestDto;

import java.util.List;

public interface ProductService {
     List<ProductDto> findAll();

     ProductDto findProduct(Long id);

     ProductDto saveProduct(ProductRequestDto productRequestDto);

     ProductDto updateProduct(ProductRequestDto productRequestDto, Long id);
}
