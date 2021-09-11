package cz.abdykili.eshop.controller;


import cz.abdykili.eshop.model.PreviewResponse;
import cz.abdykili.eshop.model.ProductResponseDto;
import cz.abdykili.eshop.model.ProductRequestDto;
import cz.abdykili.eshop.service.impl.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @GetMapping
    public List<ProductResponseDto> findAll() {
        return productService.findAll();
    }

    @GetMapping("{id}")
    public ProductResponseDto findProductById(@PathVariable("id") Long id) {
        return productService.findProduct(id);
    }

    @PostMapping
    public ProductResponseDto saveProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        return productService.saveProduct(productRequestDto);
    }

    @PutMapping("{id}")
    public ProductResponseDto updateProduct(@Valid @RequestBody ProductRequestDto productRequestDto, @PathVariable("id") Long id) {
        return productService.updateProduct(productRequestDto, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }


    @PostMapping(value="{id}/preview", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addPreview(@PathVariable Long id, @RequestPart("file") MultipartFile file){
        productService.addPreview(id, file);
    }

    @GetMapping(value = "{id}/preview", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPreview(@PathVariable("id") Long id, HttpServletResponse response){
        PreviewResponse previewResponse = productService.getPreview(id);
        response.addHeader("Content-Disposition", "inline; filename="+previewResponse.getFilename());
        return previewResponse.getBytes();
    }
}
