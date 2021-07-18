package cz.abdykili.eshop.api;


import cz.abdykili.eshop.payload.ProductRequestDto;
import cz.abdykili.eshop.payload.ProductResponseDto;
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
    public List<ProductResponseDto> findAll(){
        return productService.findAll();
    }

    @GetMapping("{id}" )
    public ProductResponseDto findProductById(@PathVariable("id") Long id){
        return productService.findProduct(id);
    }

    @PostMapping
    public ProductResponseDto saveProduct(@RequestBody ProductRequestDto productRequestDto){
        return productService.saveProduct(productRequestDto);
    }


}
