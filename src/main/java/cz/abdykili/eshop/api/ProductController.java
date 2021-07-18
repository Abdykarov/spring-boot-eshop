package cz.abdykili.eshop.api;


import cz.abdykili.eshop.model.ProductDto;
import cz.abdykili.eshop.payload.ProductRequestDto;
import cz.abdykili.eshop.service.ProductServiceImpl;
import org.springframework.http.HttpStatus;
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
    public ProductDto findProductById(@PathVariable("id") Long id){
        return productService.findProduct(id);
    }

    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductRequestDto productRequestDto){
        return productService.saveProduct(productRequestDto);
    }

    @PutMapping("{id}")
    public ProductDto updateProduct(@RequestBody ProductRequestDto productRequestDto, @PathVariable("id") Long id){
        return productService.updateProduct(productRequestDto, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }

}
