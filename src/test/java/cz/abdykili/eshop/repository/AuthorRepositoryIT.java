package cz.abdykili.eshop.repository;

import cz.abdykili.eshop.domain.AuthorEntity;
import cz.abdykili.eshop.domain.GenreEntity;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class AuthorRepositoryIT implements WithAssertions {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void save(){
        AuthorEntity authorEntity = new AuthorEntity()
                .setName("author");
        AuthorEntity persistedAuthor = testEntityManager.persistAndFlush(authorEntity);
        testEntityManager.detach(persistedAuthor);
        Optional<AuthorEntity> author = authorRepository.findById(persistedAuthor.getId());
        assertThat(author).isNotEmpty();
        AuthorEntity retrievedAuthor = author.get();
        SoftAssertions.assertSoftly(softAssertions -> {
            assertThat(retrievedAuthor.getName()).isEqualTo(authorEntity.getName());
        });
    }

}