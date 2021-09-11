package cz.abdykili.eshop.repository;

import cz.abdykili.eshop.domain.AuthorEntity;
import cz.abdykili.eshop.domain.GenreEntity;
import cz.abdykili.eshop.domain.Product;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryIT implements WithAssertions {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void save(){
        AuthorEntity author = new AuthorEntity().setName("Author");
        AuthorEntity persistedAuthor = testEntityManager.persistAndFlush(author);
        testEntityManager.detach(persistedAuthor);
        Optional<AuthorEntity> authorRepositoryById = authorRepository.findById(persistedAuthor.getId());
        assertThat(authorRepositoryById).isNotEmpty();
        AuthorEntity authorEntity = authorRepositoryById.get();

        GenreEntity genreEntity = new GenreEntity()
                .setName("name");
        GenreEntity persistedGenre = testEntityManager.persistAndFlush(genreEntity);
        testEntityManager.detach(persistedGenre);
        Optional<GenreEntity> genre = genreRepository.findById(persistedGenre.getId());
        assertThat(genre).isNotEmpty();
        GenreEntity retrievedGenre = genre.get();

        Product product = new Product()
                .setName("name")
                .setAuthor(authorEntity)
                .setGenre(retrievedGenre);

        Product persistedProduct = testEntityManager.persistAndFlush(product);
        testEntityManager.detach(persistedProduct);
        Optional<Product> productRepositoryById = productRepository.findById(persistedProduct.getId());
        assertThat(productRepositoryById).isNotEmpty();
        Product retrievedProduct = productRepositoryById.get();
        SoftAssertions.assertSoftly(softAssertions -> {
            assertThat(retrievedProduct.getName()).isEqualTo(product.getName());
            assertThat(retrievedProduct.getAuthor().getName()).isEqualTo(author.getName());
            assertThat(retrievedProduct.getGenre().getName()).isEqualTo(genreEntity.getName());
        });
    }
}