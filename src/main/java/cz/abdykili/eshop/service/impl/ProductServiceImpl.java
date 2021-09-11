package cz.abdykili.eshop.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import cz.abdykili.eshop.configuration.AmazonConfig;
import cz.abdykili.eshop.domain.AuthorEntity;
import cz.abdykili.eshop.domain.GenreEntity;
import cz.abdykili.eshop.domain.Product;
import cz.abdykili.eshop.exception.ItaRequestException;
import cz.abdykili.eshop.exception.ItaResponseException;
import cz.abdykili.eshop.exception.ProductNotFoundException;
import cz.abdykili.eshop.mapperInterface.ProductMapper;
import cz.abdykili.eshop.model.PreviewResponse;
import cz.abdykili.eshop.model.ProductResponseDto;
import cz.abdykili.eshop.model.ProductRequestDto;
import cz.abdykili.eshop.repository.AuthorRepository;
import cz.abdykili.eshop.repository.GenreRepository;
import cz.abdykili.eshop.repository.ProductRepository;
import cz.abdykili.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
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
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final AmazonS3 s3;
    private final AmazonConfig amazonConfig;

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> findAll() {
        List<ProductResponseDto> products = productRepository.findAll().stream()
                .map(p -> productMapper.mapToResponse(p))
                .collect(Collectors.toList());

        log.info("Returning {} products from repository", products.size());
        log.debug("Returning the products {}", products);

        return products;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto findProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found!",
                        HttpStatus.NOT_FOUND));
        ProductResponseDto responseProduct = productMapper.mapToResponse(product);

        log.info("Returning the product with id {} from repository", product.getId());
        log.debug("Returning the product {}", responseProduct);

        return responseProduct;
    }

    @Override
    @Transactional
    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto) {
        log.info("Saving a new product");
        log.debug("Saving a new product - Incoming payload {} ", productRequestDto);

        Product newProduct = productMapper.mapToEntity(productRequestDto);

        setAuthorAndGenre(productRequestDto, newProduct);

        final Product savedProduct = productRepository.save(newProduct);
        ProductResponseDto productResponseDto = productMapper.mapToResponse(savedProduct);



        log.debug("Saving a new product - Incoming payload {}, outgoing payload {} ", productRequestDto, productResponseDto);

        return productResponseDto;
    }

    private void setAuthorAndGenre(ProductRequestDto productRequestDto,
                                   Product newProduct){
        final GenreEntity genre = genreRepository.findById(productRequestDto.getIdGenre())
                .orElseThrow(() -> new ProductNotFoundException("Genre not found with id " + productRequestDto.getIdGenre(),
                        HttpStatus.NOT_FOUND));

        final AuthorEntity author = authorRepository.findById(productRequestDto.getIdAuthor())
                .orElseThrow(() -> new ProductNotFoundException("Author not found with id " + productRequestDto.getIdAuthor(),
                        HttpStatus.NOT_FOUND));

        newProduct
                .setAuthor(author)
                .setGenre(genre);
    }

    @Override
    @Transactional
    public ProductResponseDto updateProduct(ProductRequestDto productRequestDto, Long id) {
        log.info("Updating product");
        log.debug("Updating product with id: {} with payload {}", id, productRequestDto);

        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found", HttpStatus.NOT_FOUND));

        setAuthorAndGenre(productRequestDto, product);

        productMapper.mapToExistingEntity(product, productRequestDto);

        final ProductResponseDto productDto = productMapper.mapToResponse(product);
        log.debug("Returning updated product {}", productDto);
        return productDto;

    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        log.info("Deleting the product with id {}", id);
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException("Product was not found with id " + id, HttpStatus.NOT_FOUND);
        }

    }

    @Override
    @Transactional
    public void addPreview(Long id, MultipartFile file) {

        final Product productRepositoryById = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product doesnt exist", HttpStatus.NOT_FOUND));
        productRepositoryById.setPreviewFileName(file.getOriginalFilename());
        try {
            s3.putObject(amazonConfig.getBucketName(),
                    file.getOriginalFilename(),
                    file.getInputStream(),
                    new ObjectMetadata());
        } catch (IOException e) {
            throw new ItaRequestException("Failed to upload the file", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PreviewResponse getPreview(Long id) {
        final Product productRepositoryById = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product doesnt exist", HttpStatus.NOT_FOUND));
        String fileName = productRepositoryById.getPreviewFileName();
        try {
            S3Object object = s3.getObject(amazonConfig.getBucketName(), fileName);
            S3ObjectInputStream objectContent = object.getObjectContent();
            return new PreviewResponse()
                    .setFilename(fileName)
                    .setBytes(IOUtils.toByteArray(objectContent));
        } catch (AmazonServiceException | IOException e) {
            throw new ItaRequestException("Failed to download the file", HttpStatus.BAD_REQUEST);
        }
    }

}
