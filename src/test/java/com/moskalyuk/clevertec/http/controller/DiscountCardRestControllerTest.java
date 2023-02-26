package com.moskalyuk.clevertec.http.controller;

import com.moskalyuk.clevertec.util.BaseClass;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.moskalyuk.clevertec.dto.discountCard.DiscountCardCreateEditDto;
import com.moskalyuk.clevertec.dto.discountCard.DiscountCardReadDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
class DiscountCardRestControllerTest extends BaseClass {

    private final MockMvc mockMvc;

    @Autowired
    public DiscountCardRestControllerTest(MockMvc mockMvc) {
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
    void getDiscountCardList() throws Exception {
        String uri = "/discount";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        DiscountCardReadDto[] discountCardReadDtos = mapFromJson(content, DiscountCardReadDto[].class);
        assertTrue(discountCardReadDtos.length > 0);
    }

    @Test
    void getDiscountCardById() throws Exception {
        String uri = "/discount/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        DiscountCardReadDto discountCardReadDto = mapFromJson(content, DiscountCardReadDto.class);
        assertEquals(discountCardReadDto.getId(),1);

    }

    @Test
    void createDiscountCard() throws Exception {
        String uri = "/discount";
        DiscountCardCreateEditDto createDto = DiscountCardCreateEditDto.newBuilder()
                .setBit(10)
                .setName("test")
                .build();

        String inputJson = mapToJson(createDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        DiscountCardReadDto placeReadDto = mapFromJson(content, DiscountCardReadDto.class);
        assertNotNull(placeReadDto.getId());
    }

    @Test
    void updateDiscountCard() throws Exception {
        String uri = "/discount/2";
        DiscountCardCreateEditDto editDto = DiscountCardCreateEditDto.newBuilder()
                .setBit(10)
                .setName("test1")
                .build();

        String inputJson = mapToJson(editDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        DiscountCardReadDto eventReadDto = mapFromJson(content, DiscountCardReadDto.class);
        assertNotNull(eventReadDto.getId());
        assertEquals(eventReadDto.getBit(),editDto.getBit());
        assertEquals(eventReadDto.getName(),editDto.getName());
    }

    @Test
    void deleteDiscountCard() throws Exception {
        String uri = "/discount/2";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "");
    }

}