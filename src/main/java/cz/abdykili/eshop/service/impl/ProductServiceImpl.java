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
        logger.debug("Returning the products {}", products);

        return products;
    }

    @Override
    public ProductResponseDto findProduct(Long id){
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found!",
                        HttpStatus.NOT_FOUND));
        ProductResponseDto responseProduct = mapToResponse(product);

        logger.info("Returning the product with id {} from repository", product.getId());
        logger.debug("Returning the product {}", responseProduct);

        return responseProduct;
    }

    @Override
    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto){
        logger.info("Saving a new product");
        logger.debug("Saving a new product - Incoming payload {} ", productRequestDto);

        Product newProduct = mapToEntity(productRequestDto);
        final Product savedProduct = productRepository.save(newProduct);
        ProductResponseDto productResponseDto = mapToResponse(savedProduct);

        logger.debug("Saving a new product - Incoming payload {}, outgoing payload {} ", productRequestDto, productResponseDto);

        return productResponseDto;
    }

    @Override
    public ProductResponseDto updateProduct(ProductRequestDto productRequestDto, Long id){
        logger.info("Updating an existing product with id {}", id);
        logger.debug("Updating an existing product with id {} - Incoming payload {} ", id, productRequestDto);
        if(productRepository.existsById(id)){

            Product productToSave = mapToEntity(productRequestDto)
                    .setId(id);

            final Product updatedProduct = productRepository.save(productToSave);
            ProductResponseDto productResponseDto = mapToResponse(updatedProduct);

            logger.debug("Updating an existing product | incoming payload {}, outgoing payload {} ", productRequestDto, productResponseDto);

            return productResponseDto;
        }
        else{
            logger.error("Updating an existing product with id {} | Entity with id {} was not found", id, id);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id){
        logger.info("Deleting the product with id {}", id);
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
        }else{
            logger.error("Deleting the product with id {} | Entity with id {} was not found", id, id);
        }
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
