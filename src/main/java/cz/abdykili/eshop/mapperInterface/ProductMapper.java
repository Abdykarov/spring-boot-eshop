package cz.abdykili.eshop.mapperInterface;

import cz.abdykili.eshop.domain.Product;
import cz.abdykili.eshop.model.ProductRequestDto;
import cz.abdykili.eshop.model.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface ProductMapper {

    Product mapToEntity(ProductRequestDto productRequestDto);

    ProductResponseDto mapToResponse(Product product);


    Product mapToExistingEntity(@MappingTarget Product product, ProductRequestDto productRequestDto);

}
