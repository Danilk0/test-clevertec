package com.moskalyuk.clevertec.service;

import com.moskalyuk.clevertec.database.repository.DiscountCardRepository;
import com.moskalyuk.clevertec.dto.discountCard.DiscountCardCreateEditDto;
import com.moskalyuk.clevertec.dto.discountCard.DiscountCardReadDto;
import com.moskalyuk.clevertec.mapper.discountCard.DiscountCardCreateEditMapper;
import com.moskalyuk.clevertec.mapper.discountCard.DiscountCardReadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountCardService {
    private final DiscountCardRepository discountCardRepository;
    private final DiscountCardReadMapper discountCardReadMapper;
    private final DiscountCardCreateEditMapper discountCardCreateEditMapper;

    @Autowired
    public DiscountCardService(DiscountCardRepository discountCardRepository, DiscountCardReadMapper discountCardReadMapper, DiscountCardCreateEditMapper discountCardCreateEditMapper) {
        this.discountCardRepository = discountCardRepository;
        this.discountCardReadMapper = discountCardReadMapper;
        this.discountCardCreateEditMapper = discountCardCreateEditMapper;
    }
    @Transactional
    public List<DiscountCardReadDto> findAll() {
        return discountCardRepository.findAll().stream()
                .map(discountCardReadMapper::map)
                .toList();
    }


    @Transactional
    public Optional<DiscountCardReadDto> findById(Integer id){
        return discountCardRepository.findById(id)
                .map(discountCardReadMapper::map);
    }

    @Transactional
    public DiscountCardReadDto create(DiscountCardCreateEditDto editDto){
        return Optional.of(editDto)
                .map(discountCardCreateEditMapper::map)
                .map(discountCardRepository::save)
                .map(discountCardReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<DiscountCardReadDto> update(Integer id, DiscountCardCreateEditDto discountCardCreateEditDto) {
        return discountCardRepository.findById(id)
                .map(entity ->discountCardCreateEditMapper.map(discountCardCreateEditDto, entity))
                .map(discountCardRepository::saveAndFlush)
                .map(discountCardReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        return discountCardRepository.findById(id)
                .map(entity -> {
                    discountCardRepository.delete(entity);
                    discountCardRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
