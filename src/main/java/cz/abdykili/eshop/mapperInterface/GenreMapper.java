package cz.abdykili.eshop.mapperInterface;

import cz.abdykili.eshop.domain.GenreEntity;
import cz.abdykili.eshop.model.GenreResponseDto;
import org.mapstruct.Mapper;

@Mapper(uses = ProductMapper.class)
public interface GenreMapper {
    GenreResponseDto toDto(GenreEntity genreEntity);
}
