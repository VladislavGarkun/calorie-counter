package com.ibagroup.common.service.impl;

import com.ibagroup.common.dao.mongo.collection.Product;
import com.ibagroup.common.dao.mongo.repository.ProductRepository;
import com.ibagroup.common.domain.dto.ProductDto;
import com.ibagroup.common.domain.dto.ProductRegistrationDto;
import com.ibagroup.common.domain.mapper.ProductMapper;
import com.ibagroup.common.domain.mapper.ProductRegistrationMapper;
import com.ibagroup.common.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final Integer PROTEINS_COEFFICIENT = 4;
    private static final Integer CARBS_COEFFICIENT = 4;
    private static final Integer FATS_COEFFICIENT = 9;

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductRegistrationMapper productRegistrationMapper;

    public void createProduct(ProductRegistrationDto productRegistrationDto) {
        Float calories = PROTEINS_COEFFICIENT * productRegistrationDto.getProteins()
                + CARBS_COEFFICIENT * productRegistrationDto.getCarbs()
                + FATS_COEFFICIENT * productRegistrationDto.getFats();
        Product product = productRegistrationMapper.toEntity(productRegistrationDto);
        product.setCalories(calories);
        productRepository.insert(product);
    }

    public List<ProductDto> getProducts(){
        return productMapper.toDto(productRepository.findAll());
    }

    @Override
    public List<String> getProductNames() {
        return productMapper.toDto(productRepository.findAll())
                .stream()
                .map(ProductDto::getName)
                .collect(Collectors.toList());
    }

    public String getProductIdByName(String name){
        return productRepository.findProductByName(name).map(Product::getId).orElse(null);
    }

    public Map<String, ProductDto> getProductsByIds(List<String> productIds) {
        return productRepository.findProductsByIdIn(productIds)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toMap(ProductDto::getId, Function.identity()));
    }
}
