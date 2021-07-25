package cz.abdykili.eshop.service.impl;

import cz.abdykili.eshop.domain.Product;
import cz.abdykili.eshop.exception.ProductNotFoundException;
import cz.abdykili.eshop.model.ProductResponseDto;
import cz.abdykili.eshop.model.ProductRequestDto;
import cz.abdykili.eshop.repository.ProductRepository;
import cz.abdykili.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private final ProductRepository productRepository;

    @Override
    public List<ProductResponseDto> findAll(){
        List<ProductResponseDto> products = productRepository.findAll().stream()
                .map(p -> mapToResponse(p))
                .collect(Collectors.toList());

        logger.info("Returning {} products from repository", products.size());
        if(logger.isDebugEnabled()){
            for(ProductResponseDto product : products){
                logger.debug("Returning the product {}", product);
            }
        }

        return products;
    }

    @Override
    public ProductResponseDto findProduct(Long id){
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found!",
                        HttpStatus.NOT_FOUND));
        ProductResponseDto responseProduct = mapToResponse(product);

        logger.info("Returning the product with id {} from repository", product.getId());
        if(logger.isDebugEnabled()){
            logger.debug("Returning the product {}", responseProduct);
        }

        return responseProduct;
    }

    @Override
    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto){
            Product newProduct = mapToEntity(productRequestDto);
            final Product savedProduct = productRepository.save(newProduct);
            ProductResponseDto productResponseDto = mapToResponse(savedProduct);

            logger.info("Saving the product with id {}", savedProduct.getId());
            if(logger.isDebugEnabled()){
                logger.debug("Incoming payload {}, outgoing payload {} ", productRequestDto, productResponseDto);
            }

            return productResponseDto;
    }

    @Override
    public ProductResponseDto updateProduct(ProductRequestDto productRequestDto, Long id){
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found!"));
        Product productToSave = mapToEntity(productRequestDto)
                .setId(product.getId());
        final Product updatedProduct = productRepository.save(productToSave);
        ProductResponseDto productResponseDto = mapToResponse(updatedProduct);

        logger.info("Updating the product with id {}", product.getId());
        if(logger.isDebugEnabled()) {
            logger.debug("Incoming payload {}, outgoing payload {} ", productRequestDto, productResponseDto);
        }

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long id){
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found!"));

        logger.info("Deleting the product with id {}", product.getId());

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
