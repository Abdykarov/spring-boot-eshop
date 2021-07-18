package cz.abdykili.eshop.payload;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long stock;
    private String image;
}
