package com.moskalyuk.clevertec.http.controller;

import com.moskalyuk.clevertec.dto.discountCard.DiscountCardCreateEditDto;
import com.moskalyuk.clevertec.dto.discountCard.DiscountCardReadDto;
import com.moskalyuk.clevertec.service.DiscountCardService;

import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/discount")
public class DiscountCardRestController {

    private final DiscountCardService discountCardService;

    @Autowired
    public DiscountCardRestController(DiscountCardService discountCardService) {
        this.discountCardService = discountCardService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DiscountCardReadDto> findAll() {
        return discountCardService.findAll();
    }

    @GetMapping("/{id}")
    public DiscountCardReadDto findById(@PathVariable("id") Integer id) {
        return discountCardService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DiscountCardReadDto create(@Validated() @RequestBody DiscountCardCreateEditDto createDto) {
        return discountCardService.create(createDto);
    }

    @PutMapping("/{id}")
    public DiscountCardReadDto update(@PathVariable("id") Integer id,
                               @RequestBody @Validated() DiscountCardCreateEditDto editDto) {
        return discountCardService.update(id, editDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return discountCardService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
