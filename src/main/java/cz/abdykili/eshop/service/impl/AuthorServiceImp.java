package cz.abdykili.eshop.service.impl;

import cz.abdykili.eshop.domain.AuthorEntity;
import cz.abdykili.eshop.mapperInterface.AuthorMapper;
import cz.abdykili.eshop.model.AuthorResponseDto;
import cz.abdykili.eshop.repository.AuthorRepository;
import cz.abdykili.eshop.service.AuthorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class AuthorServiceImp implements AuthorService {

    private final AuthorRepository repository;
    private final AuthorMapper authorMapper;

    @Override
    @Transactional(readOnly = true)
    public AuthorResponseDto findById(Long id) {
        log.info("Finding author by id - {}", id);
        final AuthorEntity byId = repository.getById(id);
        AuthorResponseDto authorResponseDto = authorMapper.toResponse(byId);
        log.debug("Finding author by id | Author dto - {}", authorResponseDto);
        return authorResponseDto;
    }

    @Override
    public List<AuthorResponseDto> findAll() {
        log.info("Returning all authors");
        final List<AuthorEntity> authorEntities = repository.findAll();
        List<AuthorResponseDto> authorResponseDtos = authorEntities.stream()
                .map(author -> authorMapper.toResponse(author))
                .collect(Collectors.toList());
        log.debug("Returning genres | entities : {}", authorResponseDtos);
        return authorResponseDtos;
    }

}
