package cz.abdykili.eshop.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestDto {
    Long cartId;
    String name;
    String address;
}
