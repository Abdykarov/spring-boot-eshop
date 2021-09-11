package cz.abdykili.eshop.service.impl;

import cz.abdykili.eshop.domain.GenreEntity;
import cz.abdykili.eshop.mapperInterface.GenreMapper;
import cz.abdykili.eshop.model.GenreResponseDto;
import cz.abdykili.eshop.repository.GenreRepository;
import cz.abdykili.eshop.service.GenreService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class GenreServiceImp implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public List<GenreResponseDto> findAll() {
        log.info("returning all genres");
        final List<GenreEntity> genreEntities = genreRepository.findAll();
        List<GenreResponseDto> responseDtos = genreEntities.stream()
                .map(genreEntity -> genreMapper.toDto(genreEntity))
                .collect(Collectors.toList());
        log.debug("Returning genres | entities : {}", responseDtos);
        return responseDtos;
    }
}
