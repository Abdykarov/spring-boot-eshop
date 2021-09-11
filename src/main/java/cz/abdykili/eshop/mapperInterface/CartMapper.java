package cz.abdykili.eshop.mapperInterface;

import cz.abdykili.eshop.domain.CartEntity;
import cz.abdykili.eshop.model.CartResponseDto;
import org.mapstruct.Mapper;

@Mapper(uses = ProductMapper.class)
public interface CartMapper {
    CartResponseDto toResponse(CartEntity cartEntity);
}
