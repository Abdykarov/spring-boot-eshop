package cz.abdykili.eshop.repository;

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
class GenreRepositoryIT implements WithAssertions {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void save(){
        GenreEntity genreEntity = new GenreEntity()
                .setName("name");
        GenreEntity persistedGenre = testEntityManager.persistAndFlush(genreEntity);
        testEntityManager.detach(persistedGenre);
        Optional<GenreEntity> genre = genreRepository.findById(persistedGenre.getId());
        assertThat(genre).isNotEmpty();
        GenreEntity retrievedGenre = genre.get();
        SoftAssertions.assertSoftly(softAssertions -> {
             assertThat(retrievedGenre.getName()).isEqualTo(genreEntity.getName());
        });
    }
}