package cz.abdykili.eshop.model;

import cz.abdykili.eshop.domain.Product;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponseDto {
    Long id;
    String orderStatus;
    String name;
    String address;
    List<ProductResponseDto> products;

}
