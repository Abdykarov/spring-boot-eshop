package cz.abdykili.eshop.domain;

import cz.abdykili.eshop.validation.NameConstraint;
import cz.abdykili.eshop.validation.UrlConstraint;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Product extends AbstractEntity{

    @NameConstraint
    private String name;

    @Column(length = 512)
    private String description;
    private BigDecimal price;
    private Long stock;
    private String previewFileName;

    @UrlConstraint
    private String image;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

}
