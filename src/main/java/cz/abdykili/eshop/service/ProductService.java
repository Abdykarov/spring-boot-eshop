package cz.abdykili.eshop.service;

import cz.abdykili.eshop.model.PreviewResponse;
import cz.abdykili.eshop.model.ProductResponseDto;
import cz.abdykili.eshop.model.ProductRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Product service interface
 */
public interface ProductService {
    /**
     * Method returns all products
     * @return
     */
    List<ProductResponseDto> findAll();

    /**
     * Method returns a product by id
     * @param id
     * @return
     */
    ProductResponseDto findProduct(Long id);

    /**
     * Method saves a new product
     * @param productRequestDto
     * @return
     */
    ProductResponseDto saveProduct(ProductRequestDto productRequestDto);

    /**
     * Method updates an existing product by his id
     * @param productRequestDto
     * @param id
     * @return
     */
    ProductResponseDto updateProduct(ProductRequestDto productRequestDto, Long id);

    /**
     * Method deletes existing product by id
     * @param id
     */
    void deleteProduct(Long id);

    /**
     * Method adds pdf to the aws storage
     * @param id
     * @param file
     */
    void addPreview(Long id, MultipartFile file);

    /**
     * Method downloads pdf from aws storage
     * @param id
     * @return
     */
    PreviewResponse getPreview(Long id);
}
