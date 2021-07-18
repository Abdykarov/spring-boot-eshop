package cz.abdykili.eshop.service;

import cz.abdykili.eshop.domain.Product;
import cz.abdykili.eshop.payload.ProductRequestDto;
import cz.abdykili.eshop.payload.ProductResponseDto;
import cz.abdykili.eshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for getting products
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponseDto> findAll(){
        return productRepository.findAll().stream()
                .map(p -> mapToResponse(p))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto findProduct(Long id){
        return productRepository.findProductById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto){
        Product newProduct = new Product()
                .setDescription(productRequestDto.getDescription())
                .setImage(productRequestDto.getImage())
                .setPrice(productRequestDto.getPrice())
                .setName(productRequestDto.getName())
                .setStock(productRequestDto.getStock());

        final Product savedProduct = productRepository.save(newProduct);

        final ProductResponseDto productResponseDto = new ProductResponseDto()
                .setDescription(savedProduct.getDescription())
                .setId(1L)
                .setImage(savedProduct.getImage())
                .setPrice(savedProduct.getPrice())
                .setStock(savedProduct.getStock())
                .setName(savedProduct.getName());

        return productResponseDto;
    }

    private ProductResponseDto mapToResponse (Product savedProduct){
        return new ProductResponseDto()
                .setDescription(savedProduct.getDescription())
                .setId(1L)
                .setImage(savedProduct.getImage())
                .setPrice(savedProduct.getPrice())
                .setStock(savedProduct.getStock())
                .setName(savedProduct.getName());
    }
}
