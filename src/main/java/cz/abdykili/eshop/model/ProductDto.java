package cz.abdykili.eshop.model;

import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private Long price;
    private Long stock;
    private String image;


}
