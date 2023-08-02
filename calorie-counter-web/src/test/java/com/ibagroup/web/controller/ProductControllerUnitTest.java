package com.ibagroup.web.controller;

import com.ibagroup.common.domain.dto.ProductRegistrationDto;
import com.ibagroup.common.service.ProductService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.VerificationCollector;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerUnitTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Rule
    public VerificationCollector verificationCollector = MockitoJUnit.collector();

    @Test
    public void whenAddProduct_givenProductRegistrationDto(){
        // given
        ProductRegistrationDto productRegistrationDto = new ProductRegistrationDto("product", 2f, 3f, 4f);

        doNothing().when(productService).createProduct(any());

        // when
        productController.addProduct(productRegistrationDto);

        // then
        // VerificationCollector will show to errors immediately
        //verify(productService).getProducts();
        //verify(productService).getProductIdByName("name");
        verify(productService).createProduct(productRegistrationDto);
    }

}
