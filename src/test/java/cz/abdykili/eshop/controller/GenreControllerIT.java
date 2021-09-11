package cz.abdykili.eshop.controller;

import cz.abdykili.eshop.domain.GenreEntity;
import cz.abdykili.eshop.model.AuthorResponseDto;
import cz.abdykili.eshop.model.GenreResponseDto;
import cz.abdykili.eshop.model.ProductResponseDto;
import cz.abdykili.eshop.repository.GenreRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GenreControllerIT implements WithAssertions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private GenreRepository genreRepository;
    @Test
    void getGenres(){
        final ResponseEntity<GenreResponseDto[]> response = testRestTemplate.getForEntity("/api/v1/genres", GenreResponseDto[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        final GenreResponseDto[] body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body).hasSize(genreRepository.findAll().size());
    }

}