package org.example.productcatalogservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.productcatalogservice.dtos.ProductDto;
import org.example.productcatalogservice.models.Product;
import org.example.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// This is for Mvc testing specifically
// Its takes the input of the class that you want to test
@WebMvcTest(ProductController.class)
class ProductControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;  // this helps us simulate the behaviour as if we are a client.

    @MockBean  // We are using MockBean instead of Autowire, because we are not testing productService class here.
    private IProductService productService;

    @Autowired
    private ObjectMapper mapper;


    @Test
    @DisplayName("testing product controller for getProducts with a valid Id")
    public void test_GetProductById_WithValidId_ExpectingStatus200_AndReturnsProduct() throws Exception {
        // arrange
        Long id = 1l;
        Product product = new Product();
        product.setId(id);
        product.setName("Iphone 15 Pro Max");
        product.setPrice(109000D);

        when(productService.getProductById(id))
            .thenReturn(product);

        // act and assert
        mockMvc.perform(get("/products/{id}", id))
            .andExpect(status().isOk())
            .andExpect(content().string(mapper.writeValueAsString(product)));

    }

    @Test
    public void test_createProduct_WithValidProduct_ExpectingStatus201_AndReturnsProduct() throws Exception {
        // arrange
        ProductDto productDto = new ProductDto();
        Long id = 1l;
        productDto.setId(1l);
        productDto.setName("Iphone 15 Pro Max");
        productDto.setPrice(109000D);

        Product prodcut = new Product();
        prodcut.setId(1l);
        prodcut.setName("Iphone 15 Pro Max");
        prodcut.setPrice(109000D);

        when(productService.createProduct(any(Product.class)))
            .thenReturn(prodcut);

        mockMvc.perform(post("/products").content(mapper.writeValueAsString(productDto)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(mapper.writeValueAsString(productDto)));

    }


}