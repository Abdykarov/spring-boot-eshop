package cz.abdykili.eshop.controller;

import cz.abdykili.eshop.model.CartResponseDto;
import cz.abdykili.eshop.model.GenreResponseDto;
import cz.abdykili.eshop.repository.CartRepository;
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
class CartControllerIT implements WithAssertions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void createCart(){
        final ResponseEntity<CartResponseDto> response = testRestTemplate.postForEntity("/api/v1/carts/product/1002",null, CartResponseDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        final CartResponseDto body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getProducts().get(0).getId()).isEqualTo(1002);
        assertThat(body.getProducts().get(0).getName()).isEqualTo("name");
    }

    @Test
    void getCart(){
        final ResponseEntity<CartResponseDto> response = testRestTemplate.getForEntity("/api/v1/carts/3", CartResponseDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        final CartResponseDto body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getProducts().get(0).getId()).isEqualTo(1002);
        assertThat(body.getProducts().get(0).getName()).isEqualTo("name");
    }

}