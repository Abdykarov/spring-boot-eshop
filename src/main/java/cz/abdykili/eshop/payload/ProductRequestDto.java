package cz.abdykili.eshop.payload;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long stock;
    private String image;
}
