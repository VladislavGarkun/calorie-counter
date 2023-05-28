package com.ibagroup.common.service;

import com.ibagroup.common.domain.dto.ProductDto;
import com.ibagroup.common.domain.dto.ProductRegistrationDto;
import com.ibagroup.common.domain.mapper.ProductMapper;
import com.ibagroup.common.domain.mapper.ProductRegistrationMapper;
import com.ibagroup.common.mongo.collection.Product;
import com.ibagroup.common.mongo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final Integer PROTEINS_COEFFICIENT = 4;
    private static final Integer CARBS_COEFFICIENT = 4;
    private static final Integer FATS_COEFFICIENT = 9;

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductRegistrationMapper productRegistrationMapper;

    public void createProduct(@NotNull @Valid ProductRegistrationDto productRegistrationDto) {
        Float calories = PROTEINS_COEFFICIENT * productRegistrationDto.getProteins()
                + CARBS_COEFFICIENT * productRegistrationDto.getCarbs()
                + FATS_COEFFICIENT * productRegistrationDto.getFats();
        Product product = productRegistrationMapper.toEntity(productRegistrationDto);
        product.setCalories(calories);
        productRepository.insert(product);
    }

    public List<ProductDto> getProducts(){
        return productRepository.findAll().stream().map(productMapper::toDto).collect(Collectors.toList());
    }

    public String getProductIdByName(String name){
        return productRepository.findProductByName(name).map(Product::getId).orElse(null);
    }

}
