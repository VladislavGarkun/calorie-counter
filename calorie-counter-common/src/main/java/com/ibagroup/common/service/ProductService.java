package com.ibagroup.common.service;

import com.ibagroup.common.domain.dto.ProductDto;
import com.ibagroup.common.domain.mapper.ProductMapper;
import com.ibagroup.common.mongo.collection.Product;
import com.ibagroup.common.mongo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final Integer PROTEINS_COEFFICIENT = 4;
    private static final Integer CARBS_COEFFICIENT = 4;
    private static final Integer FATS_COEFFICIENT = 9;

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public void addProduct(ProductDto productDto){
        Float calories = PROTEINS_COEFFICIENT * productDto.getProteins() + CARBS_COEFFICIENT * productDto.getCarbs() + FATS_COEFFICIENT * productDto.getFats();
        Product product = productMapper.toEntity(productDto);
        product.setCalories(calories);
        productRepository.insert(product);
    }

}
