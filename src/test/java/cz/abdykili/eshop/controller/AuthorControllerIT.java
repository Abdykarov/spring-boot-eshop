package cz.abdykili.eshop.controller;

import cz.abdykili.eshop.model.AuthorResponseDto;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorControllerIT implements WithAssertions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void findAll() {

        final ResponseEntity<AuthorResponseDto[]> response = testRestTemplate.getForEntity("/api/v1/authors",AuthorResponseDto[].class);
        AuthorResponseDto[] body = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(body).isNotNull();
        assertThat(body).hasSize(1);
    }

    @Test
    void findById() {

        final ResponseEntity<AuthorResponseDto> response = testRestTemplate.getForEntity("/api/v1/authors/2",AuthorResponseDto.class);
        AuthorResponseDto body = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(body).isNotNull();
        assertThat(body.getId()).isEqualTo(2);
    }
}