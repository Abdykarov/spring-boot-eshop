//package cz.abdykili.eshop.controller;
//import cz.abdykili.eshop.model.ProductResponseDto;
//import cz.abdykili.eshop.service.impl.ProductServiceImpl;
//import org.assertj.core.api.WithAssertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@WebMvcTest({ProductController.class})
//@MockBean(JpaMetamodelMappingContext.class)
//class ProductControllerTest implements WithAssertions {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ProductServiceImpl productService;
//
//    @Test
//    void findAll() throws Exception{
//
//        ProductResponseDto product = new ProductResponseDto()
//                .setName("product");
//
//        when(productService.findAll())
//                .thenReturn(List.of(product));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products"))
//                .andExpect(status().isOk());
//
//
//
//    }
//}