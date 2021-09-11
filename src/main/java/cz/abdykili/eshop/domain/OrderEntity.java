package cz.abdykili.eshop.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity extends AbstractEntity{

    String name;
    String address;
    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @ManyToMany
    @JoinTable(
            name = "r_order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<Product> products;
}
