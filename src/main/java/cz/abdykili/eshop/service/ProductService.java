package cz.abdykili.eshop.service;

import cz.abdykili.eshop.model.ProductDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for getting products
 */
@Service
public class ProductService {

    private static List<ProductDto> products;
    private ProductDto product1;
    private ProductDto product2;
    private ProductDto product3;

    public ProductService(){
        products = new ArrayList<>();
        //init products
        product1 = new ProductDto();
        product1.setId(0L);
        product1.setName("Anette");
        product1.setDescription("Film about anette");
        product1.setImage("https://www.cinemacity.cz/xmedia-cw/repo/feats/posters/4465S2R-md.jpg");
        product1.setPrice(new BigDecimal(200));

        product2 = new ProductDto();
        product2.setId(1L);
        product2.setName("Anette2");
        product2.setDescription("Film about anette");
        product2.setImage("https://www.cinemacity.cz/xmedia-cw/repo/feats/posters/4465S2R-md.jpg");
        product2.setPrice(new BigDecimal(200));


        product3 = new ProductDto();

        product3.setId(2L);
        product3.setName("Anette3");
        product3.setDescription("Film about anette");
        product3.setImage("https://www.cinemacity.cz/xmedia-cw/repo/feats/posters/4465S2R-md.jpg");
        product3.setPrice(new BigDecimal(200));

        products.add(product1);
        products.add(product2);
        products.add(product3);

    }

    public List<ProductDto> findAll(){
        return products;
    }

    public ProductDto findProduct(Integer id){
        return products.get(id);
    }

}
