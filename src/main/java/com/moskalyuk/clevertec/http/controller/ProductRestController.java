package com.moskalyuk.clevertec.http.controller;

import com.moskalyuk.clevertec.dto.discountCard.DiscountCardCreateEditDto;
import com.moskalyuk.clevertec.dto.discountCard.DiscountCardReadDto;
import com.moskalyuk.clevertec.dto.product.ProductCreateEditDto;
import com.moskalyuk.clevertec.dto.product.ProductReadDto;
import com.moskalyuk.clevertec.service.ProductService;
import com.moskalyuk.clevertec.validation.group.CreateAction;
import com.moskalyuk.clevertec.validation.group.UpdateAction;
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
@RequestMapping("/product")
public class ProductRestController {

    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductReadDto> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductReadDto findById(@PathVariable("id") Integer id) {
        return productService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductReadDto create(@Validated() @RequestBody ProductCreateEditDto createDto) {
        return productService.create(createDto);
    }

    @PutMapping("/{id}")
    public ProductReadDto update(@PathVariable("id") Integer id,
                                      @RequestBody @Validated()ProductCreateEditDto editDto) {
        return productService.update(id, editDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return productService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
