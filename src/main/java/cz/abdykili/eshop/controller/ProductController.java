package cz.abdykili.eshop.controller;


import cz.abdykili.eshop.model.ProductResponseDto;
import cz.abdykili.eshop.model.ProductRequestDto;
import cz.abdykili.eshop.service.impl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ProductResponseDto saveProduct(@Valid @RequestBody ProductRequestDto productRequestDto){
        return productService.saveProduct(productRequestDto);
    }

    @PutMapping("{id}")
    public ProductResponseDto updateProduct(@Valid @RequestBody ProductRequestDto productRequestDto, @PathVariable("id") Long id){
        return productService.updateProduct(productRequestDto, id);
    }

    @PostMapping("/test")
    public void saveSeveralProducts(){
        productService.saveSeveralTimes();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }

}
