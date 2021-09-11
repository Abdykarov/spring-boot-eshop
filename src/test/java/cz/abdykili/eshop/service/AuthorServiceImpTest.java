package cz.abdykili.eshop.service;

import cz.abdykili.eshop.domain.AuthorEntity;
import cz.abdykili.eshop.mapperInterface.AuthorMapper;
import cz.abdykili.eshop.model.AuthorResponseDto;
import cz.abdykili.eshop.repository.AuthorRepository;
import cz.abdykili.eshop.service.impl.AuthorServiceImp;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImpTest implements WithAssertions {

    @Mock
    private AuthorRepository repository;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorServiceImp authorServiceImp;

    @Test
    void findById() {
        //assert
        final AuthorEntity authorEntity = new AuthorEntity();
        final AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        when(repository.getById(1L)).thenReturn(authorEntity);
        when(authorMapper.toResponse(authorEntity)).thenReturn(authorResponseDto);

        //act
        AuthorResponseDto byId = authorServiceImp.findById(1L);

        //assert
        verify(authorMapper).toResponse(eq(authorEntity));
        verify(repository).getById(1L);
        assertThat(byId).isEqualTo(authorResponseDto);
    }

    @Test
    void findAll() {
        //assert
        final Integer supposedSize = 1;
        AuthorEntity authorEntity = new AuthorEntity();
        when(repository.findAll()).thenReturn(Arrays.asList(
                authorEntity
                ));

        //act
        List<AuthorResponseDto> authors = authorServiceImp.findAll();

        //assert
        verify(repository).findAll();
        verify(authorMapper).toResponse(authorEntity);
        assertThat(supposedSize).isEqualTo(authors.size());
    }
}