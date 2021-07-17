package cz.abdykili.eshop.api;


import cz.abdykili.eshop.model.ProductDto;
import cz.abdykili.eshop.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin("https://master.d1z6pirlbp1239.amplifyapp.com")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService){
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> findAll(){
        return productService.findAll();
    }

    @GetMapping("{id}" )
    public ProductDto findProductById(@PathVariable("id") Integer id){
        return productService.findProduct(id);
    }

    @PostMapping
    public String testPost(){
        return "this is the post method";
    }


}
