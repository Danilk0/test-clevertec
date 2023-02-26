package com.moskalyuk.clevertec.http.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.moskalyuk.clevertec.dto.product.ProductCreateEditDto;
import com.moskalyuk.clevertec.dto.product.ProductReadDto;
import com.moskalyuk.clevertec.util.BaseClass;
import net.bytebuddy.agent.VirtualMachine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.net.ssl.SSLEngineResult;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
class ProductRestControllerTest extends BaseClass {
    private final MockMvc mockMvc;

    @Autowired
    public ProductRestControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(obj);
    }

    private  <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return objectMapper.readValue(json, clazz);
    }

    @Test
    void getProductList() throws Exception {
        String uri = "/product";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        ProductReadDto[] productReadDtos = mapFromJson(content, ProductReadDto[].class);
        assertTrue(productReadDtos.length > 0);
    }

    @Test
    void getProductById() throws Exception {
        String uri = "/product/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        ProductReadDto productReadDto = mapFromJson(content, ProductReadDto.class);
        assertEquals(productReadDto.getId(),1);

    }

    @Test
    void createProduct() throws Exception {
        String uri = "/product";
        ProductCreateEditDto createDto = ProductCreateEditDto.newBuilder()
                .setName("test")
                .setPrice(BigDecimal.valueOf(2))
                .setPromotionalItem(true)
                .build();

        String inputJson = mapToJson(createDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        ProductReadDto productReadDto = mapFromJson(content, ProductReadDto.class);
        assertNotNull(productReadDto.getId());
    }

    @Test
    void updateProduct() throws Exception {
        String uri = "/product/2";
        ProductCreateEditDto editDto = ProductCreateEditDto.newBuilder()
                .setName("test")
                .setPrice(BigDecimal.valueOf(2))
                .setPromotionalItem(true)
                .build();

        String inputJson = mapToJson(editDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        ProductReadDto productReadDto = mapFromJson(content, ProductReadDto.class);
        assertNotNull(productReadDto.getId());
        assertEquals(productReadDto.getName(),editDto.getName());
        assertEquals(productReadDto.getPrice(),editDto.getPrice());
        assertEquals(productReadDto.getPromotionalItem(),editDto.getPromotionalItem());
    }

    @Test
    void deleteProduct() throws Exception {
        String uri = "/product/2";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "");
    }

}