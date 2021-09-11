package cz.abdykili.eshop.service;

import cz.abdykili.eshop.model.GenreResponseDto;

import java.util.List;

/**
 * Genre service interface
 */
public interface GenreService {

    /**
     * Method returns all genres from repository
     * @return
     */
    public List<GenreResponseDto> findAll();

}
