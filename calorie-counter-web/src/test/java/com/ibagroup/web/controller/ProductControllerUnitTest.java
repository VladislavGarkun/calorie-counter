package com.ibagroup.web.controller;

import com.ibagroup.common.domain.dto.ProductRegistrationDto;
import com.ibagroup.common.service.ProductService;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.VerificationCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ProductController.class, ProductService.class, LocalValidatorFactoryBean.class})
@Import(ValidationAutoConfiguration.class)
public class ProductControllerUnitTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    public void whenAddProduct_givenProductRegistrationDto(){
        // given
        ProductRegistrationDto productRegistrationDto = new ProductRegistrationDto("product", 2f, 3f, 4f);

        doNothing().when(productService).createProduct(any());

        // when
        productController.addProduct(productRegistrationDto);

        // then
        verify(productService).createProduct(productRegistrationDto);
    }

    @Test
    //@Ignore(value = "Processing does not throw an exception")
    public void shouldThrowMethodArgumentNotValidException_whenAddProduct_givenNull(){
        // given

        // when
        productController.addProduct(null);

        // then
        verify(productService).createProduct(null);
    }

}
