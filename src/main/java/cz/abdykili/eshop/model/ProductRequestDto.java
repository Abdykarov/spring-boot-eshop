package cz.abdykili.eshop.model;

import cz.abdykili.eshop.validation.NameConstraint;
import cz.abdykili.eshop.validation.UrlConstraint;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.bind.Name;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ProductRequestDto {
    private Long id;
    @NotBlank
    @NameConstraint
    @Size(max = 256)
    private String name;
    @NotBlank
    @Size(max = 512)
    private String description;
    @NotNull
    @Min(1)
    private BigDecimal price;
    @Min(0)
    private Long stock;
    @NotBlank
    @UrlConstraint
    private String image;
}
