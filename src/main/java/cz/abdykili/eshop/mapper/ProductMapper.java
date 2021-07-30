package cz.abdykili.eshop.mapper;

import cz.abdykili.eshop.domain.Product;
import cz.abdykili.eshop.model.ProductRequestDto;
import cz.abdykili.eshop.model.ProductResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    Product mapToProduct(ProductRequestDto productRequestDto);
    ProductResponseDto mapToResponse(Product product);

}
