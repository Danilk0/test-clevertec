package com.moskalyuk.clevertec.service;

import com.moskalyuk.clevertec.database.repository.DiscountCardRepository;
import com.moskalyuk.clevertec.database.repository.ProductRepository;
import com.moskalyuk.clevertec.dto.discountCard.DiscountCardCreateEditDto;
import com.moskalyuk.clevertec.dto.discountCard.DiscountCardReadDto;
import com.moskalyuk.clevertec.dto.product.ProductCreateEditDto;
import com.moskalyuk.clevertec.dto.product.ProductReadDto;
import com.moskalyuk.clevertec.mapper.discountCard.DiscountCardCreateEditMapper;
import com.moskalyuk.clevertec.mapper.discountCard.DiscountCardReadMapper;
import com.moskalyuk.clevertec.mapper.product.ProductCreateEditMapper;
import com.moskalyuk.clevertec.mapper.product.ProductReadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductReadMapper productReadMapper;
    private final ProductCreateEditMapper productCreateEditMapper;


    @Autowired
    public ProductService(ProductRepository productRepository, ProductReadMapper productReadMapper, ProductCreateEditMapper productCreateEditMapper) {
        this.productRepository = productRepository;
        this.productReadMapper = productReadMapper;
        this.productCreateEditMapper = productCreateEditMapper;
    }

    @Transactional
    public List<ProductReadDto> findAll() {
        return productRepository.findAll().stream()
                .map(productReadMapper::map)
                .toList();
    }


    @Transactional
    public Optional<ProductReadDto> findById(Integer id){
        return productRepository.findById(id)
                .map(productReadMapper::map);
    }

    @Transactional
    public ProductReadDto create(ProductCreateEditDto editDto){
        return Optional.of(editDto)
                .map(productCreateEditMapper::map)
                .map(productRepository::save)
                .map(productReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<ProductReadDto> update(Integer id, ProductCreateEditDto productCreateEditDto) {
        return productRepository.findById(id)
                .map(entity -> productCreateEditMapper.map(productCreateEditDto, entity))
                .map(productRepository::saveAndFlush)
                .map(productReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        var maybeEvent = productRepository.findById(id);
        maybeEvent.ifPresent(productRepository::delete);
        return maybeEvent.isPresent();
    }
}
