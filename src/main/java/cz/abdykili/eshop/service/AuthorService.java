package cz.abdykili.eshop.service;

import cz.abdykili.eshop.model.AuthorResponseDto;

import java.util.List;

/**
 * Author service interface
 */
public interface AuthorService {

    /**
     * Methods finds Author by id
     * @param id
     * @return AuthorResponseDto
     */
    AuthorResponseDto findById(Long id);

    /**
     * Method finds all authors from repository
     * @return List of authors
     */
    List<AuthorResponseDto> findAll();

}
