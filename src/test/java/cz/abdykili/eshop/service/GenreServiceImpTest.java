package cz.abdykili.eshop.service;

import cz.abdykili.eshop.domain.GenreEntity;
import cz.abdykili.eshop.mapperInterface.GenreMapper;
import cz.abdykili.eshop.model.GenreResponseDto;
import cz.abdykili.eshop.repository.GenreRepository;
import cz.abdykili.eshop.service.impl.GenreServiceImp;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenreServiceImpTest implements WithAssertions {

    @Mock
    private GenreRepository genreRepository;
    @Mock
    private GenreMapper genreMapper;
    @InjectMocks
    private GenreServiceImp genreServiceImp;
    @Test
    void findAll() {
        //arrange
        final Integer supposedSize = 1;

        final GenreEntity genreEntity = new GenreEntity();
        final GenreResponseDto genreResponseDto = new GenreResponseDto();
        when(genreRepository.findAll()).thenReturn(Arrays.asList(
                genreEntity
        ));
        when(genreMapper.toDto(genreEntity)).thenReturn(genreResponseDto);
        //act
        final List<GenreResponseDto> returnedList = genreServiceImp.findAll();

        //assert
        verify(genreRepository).findAll();
        assertThat(supposedSize).isEqualTo(returnedList.size());
    }
}