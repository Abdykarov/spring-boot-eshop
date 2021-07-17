package cz.abdykili.eshop.service;

import cz.abdykili.eshop.model.ProductDto;

import java.util.List;

public interface ProductService {
    public List<ProductDto> findAll();

    public ProductDto findProduct(Integer id);
}
