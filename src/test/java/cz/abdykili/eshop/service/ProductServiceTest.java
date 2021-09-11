package cz.abdykili.eshop.service;

import cz.abdykili.eshop.domain.AuthorEntity;
import cz.abdykili.eshop.domain.GenreEntity;
import cz.abdykili.eshop.domain.Product;
import cz.abdykili.eshop.exception.ProductNotFoundException;
import cz.abdykili.eshop.mapperInterface.ProductMapper;
import cz.abdykili.eshop.model.AuthorResponseDto;
import cz.abdykili.eshop.model.GenreResponseDto;
import cz.abdykili.eshop.model.ProductRequestDto;
import cz.abdykili.eshop.model.ProductResponseDto;
import cz.abdykili.eshop.repository.AuthorRepository;
import cz.abdykili.eshop.repository.GenreRepository;
import cz.abdykili.eshop.repository.ProductRepository;
import cz.abdykili.eshop.service.impl.ProductServiceImpl;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest implements WithAssertions {

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void findAll() {
        //arrange
        final Integer supposedSize = 3;

        when(productRepository.findAll()).thenReturn(Arrays.asList(
                new Product(),
                new Product(),
                new Product()
        ));

        //act
        final List<ProductResponseDto> returnedList = productService.findAll();

        //assert
        verify(productRepository).findAll();
        assertThat(supposedSize).isEqualTo(returnedList.size());

    }

    @Test
    void findProduct() {
        //arrange
        final Product testProduct = (Product) new Product()
                .setId(2L);

        final ProductResponseDto productResponseDto = new ProductResponseDto();

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(testProduct));

        when(productMapper.mapToResponse(testProduct)).thenReturn(productResponseDto);

        //act
        final ProductResponseDto returnedProduct = productService.findProduct(2L);

        //assert
        verify(productRepository).findById(eq(2L));
        verify(productMapper).mapToResponse(eq(testProduct));
        assertNotNull(returnedProduct);
        assertThat(returnedProduct).isEqualTo(productResponseDto);

    }

    @Test
    public void findProduct_ProductNotFoundException() {
        //arrange
        when(productRepository.findById(1L))
                .thenReturn(Optional.empty());

        //act
        final ProductNotFoundException productNotFoundException = assertThrows(ProductNotFoundException.class,
                () -> productService.findProduct(1L));

        //assert
        verify(productRepository).findById(eq(1L));
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(productNotFoundException.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
            softAssertions.assertThat(productNotFoundException.getMessage()).isEqualTo("Product with id 1 not found!");
        });
    }

    @Test
    public void deleteProduct_TestTimes() {
        //arrange
        when(productRepository.existsById(1L)).thenReturn(true);

        //act
        productService.deleteProduct(1L);

        //assert
        verify(productRepository).existsById(eq(1L));
        verify(productRepository).deleteById(eq(1L));
    }

    @Test
    public void deleteProduct_NotFound() {
        //arrange
        when(productRepository.existsById(1L)).thenReturn(false);

        //act
        final ProductNotFoundException productNotFoundException = assertThrows(ProductNotFoundException.class,
                () -> productService.deleteProduct(1L));

        //assert
        verify(productRepository).existsById(eq(1L));
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(productNotFoundException.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
            softAssertions.assertThat(productNotFoundException.getMessage()).isEqualTo("Product was not found with id 1");
        });
    }

    @Test
    public void updateProduct_TestChangedName() {
        //arrange
        final Long id = 1L;
        final ProductRequestDto productRequestDto = new ProductRequestDto()
                .setName("newName")
                .setIdAuthor(1L)
                .setIdGenre(1L);

        final GenreEntity genreEntity = (GenreEntity) new GenreEntity().setId(productRequestDto.getIdGenre());
        final AuthorEntity authorEntity = (AuthorEntity) new AuthorEntity().setId(productRequestDto.getIdAuthor());

        final Product product = new Product()
                .setName("actualName")
                .setAuthor(authorEntity)
                .setGenre(genreEntity);

        final ProductResponseDto productResponseDto = new ProductResponseDto()
                .setName(product.getName())
                .setImage(product.getImage())
                .setAuthor(new AuthorResponseDto().setId(productRequestDto.getIdAuthor()))
                .setGenre(new GenreResponseDto().setId(productRequestDto.getIdGenre()));

        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product));
        when(genreRepository.findById(1L)).thenReturn(Optional.ofNullable(genreEntity));
        when(authorRepository.findById(1L)).thenReturn(Optional.ofNullable(authorEntity));
        when(productMapper.mapToExistingEntity(product.setName(productRequestDto.getName()),
                productRequestDto)).thenReturn(null);
        when(productMapper.mapToResponse(product)).thenReturn(productResponseDto.setName(product.getName()));

        //act
        final ProductResponseDto productResponseDtoReturned = productService.updateProduct(productRequestDto, id);

        //assert
        verify(productRepository).findById(eq(1L));
        verify(productMapper).mapToExistingEntity(product, productRequestDto);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(productResponseDtoReturned.getName()).isEqualTo("newName");
        });
    }

    @Test
    public void updateProduct_ProductNotFound() {
        //arrange
        final Long id = 1L;
        final ProductRequestDto productRequestDto = new ProductRequestDto()
                .setName("newName");
        when(productRepository.findById(1L)).thenThrow(new ProductNotFoundException("Product not found", HttpStatus.NOT_FOUND));

        //act
        final ProductNotFoundException productNotFoundException = assertThrows(ProductNotFoundException.class,
                () -> productService.updateProduct(productRequestDto, id));

        //assert
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(productNotFoundException.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
            softAssertions.assertThat(productNotFoundException.getMessage()).isEqualTo("Product not found");
        });
    }

    @Test
    public void saveProduct() {
        //arrange
        final ProductRequestDto productRequestDto = new ProductRequestDto()
                .setName("name")
                .setImage("image")
                .setIdAuthor(1L)
                .setIdGenre(1L);

        final Product product = new Product()
                .setName(productRequestDto.getName())
                .setImage(productRequestDto.getImage());

        final ProductResponseDto productResponseDto = new ProductResponseDto()
                .setName(product.getName())
                .setImage(product.getImage())
                .setAuthor(new AuthorResponseDto().setId(productRequestDto.getIdAuthor()))
                .setGenre(new GenreResponseDto().setId(productRequestDto.getIdGenre()));

        final GenreEntity genreEntity = (GenreEntity) new GenreEntity().setId(productRequestDto.getIdGenre());
        final AuthorEntity authorEntity = (AuthorEntity) new AuthorEntity().setId(productRequestDto.getIdAuthor());

        when(productMapper.mapToEntity(productRequestDto)).thenReturn(product);
        when(genreRepository.findById(1L)).thenReturn(Optional.ofNullable(genreEntity));
        when(authorRepository.findById(1L)).thenReturn(Optional.ofNullable(authorEntity));
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.mapToResponse(product)).thenReturn(productResponseDto);

        //act
        final ProductResponseDto productResponseDtoReturned = productService.saveProduct(productRequestDto);

        //assert
        verify(productRepository).save(product);
        assertThat(productResponseDtoReturned).isEqualTo(productResponseDto);


    }
}

