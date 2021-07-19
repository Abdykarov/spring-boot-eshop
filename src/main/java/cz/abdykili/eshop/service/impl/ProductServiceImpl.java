package cz.abdykili.eshop.service.impl;

import cz.abdykili.eshop.domain.Product;
import cz.abdykili.eshop.model.ProductResponseDto;
import cz.abdykili.eshop.model.ProductRequestDto;
import cz.abdykili.eshop.repository.ProductRepository;
import cz.abdykili.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import java.util.stream.Collectors;

/**
 * Service for working with the products
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Override
    public List<ProductResponseDto> findAll(){
        return productRepository.findAll().stream()
                .map(p -> mapToResponse(p))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto findProduct(Long id){
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found!"));

        return mapToResponse(product);
    }

    @Override
    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto){
        Product newProduct = mapToEntity(productRequestDto);
        final Product savedProduct = productRepository.save(newProduct);
        return mapToResponse(savedProduct);
    }

    @Override
    public ProductResponseDto updateProduct(ProductRequestDto productRequestDto, Long id){
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found!"));
        Product productToSave = mapToEntity(productRequestDto)
                .setId(product.getId());
        final Product updatedProduct = productRepository.save(productToSave);

        return mapToResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id){
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found!"));
        productRepository.deleteById(id);
    }

    private Product mapToEntity(ProductRequestDto productRequestDto){
        return new Product()
                .setDescription(productRequestDto.getDescription())
                .setImage(productRequestDto.getImage())
                .setPrice(productRequestDto.getPrice())
                .setName(productRequestDto.getName())
                .setStock(productRequestDto.getStock());
    }

    private ProductResponseDto mapToResponse (Product savedProduct){
        return new ProductResponseDto()
                .setDescription(savedProduct.getDescription())
                .setId(savedProduct.getId())
                .setImage(savedProduct.getImage())
                .setPrice(savedProduct.getPrice())
                .setStock(savedProduct.getStock())
                .setName(savedProduct.getName());
    }

}
