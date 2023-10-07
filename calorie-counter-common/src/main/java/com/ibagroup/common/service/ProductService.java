package com.ibagroup.common.service;

import com.ibagroup.common.domain.dto.ProductDto;
import com.ibagroup.common.domain.dto.ProductRegistrationDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public interface ProductService {

    void createProduct(@NotNull @Valid ProductRegistrationDto productRegistrationDto);

    List<ProductDto> getProducts();

    List<String> getProductNames();

    String getProductIdByName(String name);

    Map<String, ProductDto> getProductsByIds(List<String> productIds);

    boolean isProductRegistered(String name);

}
