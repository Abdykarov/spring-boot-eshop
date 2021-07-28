package cz.abdykili.eshop.domain;

import cz.abdykili.eshop.validation.NameConstraint;
import cz.abdykili.eshop.validation.UrlConstraint;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Accessors(chain = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NameConstraint
    private String name;

    @Column(length = 512)
    private String description;
    private BigDecimal price;
    private Long stock;

    @UrlConstraint
    private String image;
}
