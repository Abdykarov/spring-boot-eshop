package cz.abdykili.eshop.service;

import cz.abdykili.eshop.model.ProductDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for getting products
 */
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
        product1.setPrice(200L);

        product2 = new ProductDto();
        product2.setId(1L);
        product2.setName("Anette");
        product2.setDescription("Film about anette");
        product2.setImage("https://www.cinemacity.cz/xmedia-cw/repo/feats/posters/4465S2R-md.jpg");
        product2.setPrice(200L);


        product3 = new ProductDto();
        product3.setId(2L);
        product3.setName("Anette");
        product3.setDescription("Film about anette");
        product3.setImage("https://www.cinemacity.cz/xmedia-cw/repo/feats/posters/4465S2R-md.jpg");
        product3.setPrice(200L);
    }

    public List<ProductDto> findAll(){
        return products;
    }

    public ProductDto findProduct(Integer id){
        return products.get(id);
    }

}
