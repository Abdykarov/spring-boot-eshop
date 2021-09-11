package cz.abdykili.eshop.mapperInterface;

import cz.abdykili.eshop.domain.AuthorEntity;
import cz.abdykili.eshop.model.AuthorResponseDto;
import org.mapstruct.Mapper;

@Mapper(uses = ProductMapper.class)
public interface AuthorMapper {
    AuthorResponseDto toResponse(AuthorEntity authorEntity);
}