package cz.abdykili.eshop.service.impl;

import cz.abdykili.eshop.domain.Product;
import cz.abdykili.eshop.exception.ProductNotFoundException;
import cz.abdykili.eshop.mapper.ProductMapper;
import cz.abdykili.eshop.model.ProductResponseDto;
import cz.abdykili.eshop.model.ProductRequestDto;
import cz.abdykili.eshop.repository.ProductRepository;
import cz.abdykili.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Service for working with the products
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> findAll(){
        List<ProductResponseDto> products = productRepository.findAll().stream()
                .map(p -> productMapper.mapToResponse(p))
                .collect(Collectors.toList());

        log.info("Returning {} products from repository", products.size());
        log.debug("Returning the products {}", products);

        return products;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto findProduct(Long id){
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found!",
                        HttpStatus.NOT_FOUND));
        ProductResponseDto responseProduct = productMapper.mapToResponse(product);

        log.info("Returning the product with id {} from repository", product.getId());
        log.debug("Returning the product {}", responseProduct);

        return responseProduct;
    }

    @Override
    @Transactional
    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto){
        log.info("Saving a new product");
        log.debug("Saving a new product - Incoming payload {} ", productRequestDto);

        Product newProduct = productMapper.mapToProduct(productRequestDto);
        final Product savedProduct = productRepository.save(newProduct);
        ProductResponseDto productResponseDto = productMapper.mapToResponse(savedProduct);

        log.debug("Saving a new product - Incoming payload {}, outgoing payload {} ", productRequestDto, productResponseDto);

        return productResponseDto;
    }

    @Override
    @Transactional
    public ProductResponseDto updateProduct(ProductRequestDto productRequestDto, Long id){
        log.info("Updating an existing product with id {}", id);
        log.debug("Updating an existing product with id {} - Incoming payload {} ", id, productRequestDto);
        if(productRepository.existsById(id)){

            Product productToSave = productMapper.mapToProduct(productRequestDto)
                    .setId(id);

            final Product updatedProduct = productRepository.save(productToSave);
            ProductResponseDto productResponseDto = productMapper.mapToResponse(updatedProduct);

            log.debug("Updating an existing product | incoming payload {}, outgoing payload {} ", productRequestDto, productResponseDto);

            return productResponseDto;
        }
        else{
            log.error("Updating an existing product with id {} | Entity with id {} was not found", id, id);
            throw new ProductNotFoundException("Product was not found with id " + id, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) throws ProductNotFoundException{
        log.info("Deleting the product with id {}", id);
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
        }
        else if(productRepository.existsById(id) == false){
            log.error("Deleting the product with id {} | Entity with id {} was not found", id, id);
            throw new ProductNotFoundException("Product was not found with id " + id, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete this method before merging to the main branch
     * For educational purposes only
     */
    @Transactional
    public void saveSeveralTimes(){
        Product productGood = new Product()
                .setDescription("descr")
                .setImage("image.png")
                .setPrice(BigDecimal.ONE)
                .setStock(1L)
                .setName("Gfilm");
        log.info("Saving a good product {}", productGood);
        productRepository.save(productGood);


        Product productBad = new Product()
                .setDescription("descr")
                .setImage("image")
                .setPrice(BigDecimal.ONE)
                .setStock(1L)
                .setName("Gfilm");
        log.info("Saving a bad product {}", productBad);
        productRepository.save(productBad);

    }

}
