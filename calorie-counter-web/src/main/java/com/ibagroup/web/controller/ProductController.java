package com.ibagroup.web.controller;

import com.ibagroup.common.domain.dto.ProductRegistrationDto;
import com.ibagroup.common.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addProduct(@RequestBody @NotNull @Valid ProductRegistrationDto productRegistrationDto){
        productService.createProduct(productRegistrationDto);
    }

}
