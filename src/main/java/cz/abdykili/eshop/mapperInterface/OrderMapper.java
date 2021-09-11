package cz.abdykili.eshop.mapperInterface;

import cz.abdykili.eshop.domain.OrderEntity;
import cz.abdykili.eshop.model.OrderRequestDto;
import cz.abdykili.eshop.model.OrderResponseDto;
import org.mapstruct.Mapper;

@Mapper(uses = ProductMapper.class)
public interface OrderMapper {

    OrderEntity toEntity(OrderRequestDto orderRequestDto);

    OrderResponseDto toResponse(OrderEntity orderEntity);
}
