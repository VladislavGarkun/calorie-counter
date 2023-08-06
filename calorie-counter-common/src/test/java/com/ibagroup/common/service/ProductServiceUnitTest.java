package com.ibagroup.common.service;

import com.ibagroup.common.domain.dto.ProductDto;
import com.ibagroup.common.domain.dto.ProductRegistrationDto;
import com.ibagroup.common.domain.mapper.ProductMapper;
import com.ibagroup.common.domain.mapper.ProductRegistrationMapper;
import com.ibagroup.common.dao.mongo.collection.Product;
import com.ibagroup.common.dao.mongo.repository.ProductRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceUnitTest {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PRODUCT = "product";

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductRegistrationMapper productRegistrationMapper;

    @Before
    public void before(){
        System.out.println("before");
    }

    @After
    public void after(){
        System.out.println("after");
    }

    @BeforeClass
    public static void beforeClass(){
        System.out.println("beforeClass");
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("afterClass");
    }

    @Test
    public void shouldSaveProduct_whenCreateProduct_givenProductRegistrationDto() {
        // given
        ProductRegistrationDto productRegistrationDto = new ProductRegistrationDto(PRODUCT, 2f, 3f, 4f);
        Product product = prepareProduct();
        product.setCalories(56f);

        when(productRegistrationMapper.toEntity(any())).thenReturn(prepareProduct());
        when(productRepository.insert(any(Product.class))).thenReturn(product);

        // when
        productService.createProduct(productRegistrationDto);

        // then
        verify(productRegistrationMapper).toEntity(productRegistrationDto);
        verify(productRepository).insert(refEq(product));
    }

    @Test
    public void shouldReturnProductDtoList_whenGetProducts_givenNothing() {
        // given
        List<Product> productList = prepapeProductList();
        List<ProductDto> productDtoList = prepapeProductDtoList();
        List<ProductDto> expected = prepapeProductDtoList();

        when(productRepository.findAll()).thenReturn(productList);
        when(productMapper.toDto(any(List.class))).thenReturn(productDtoList);

        // when
        List<ProductDto> actual = productService.getProducts();

        // then
        verify(productRepository).findAll();
        verify(productMapper).toDto(productList);
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    public void shouldReturnEmptyProductDtoList_whenGetProducts_givenNothing() {
        // given
        List<ProductDto> expected = List.of();

        when(productRepository.findAll()).thenReturn(List.of());
        when(productMapper.toDto(any(List.class))).thenReturn(List.of());

        // when
        List<ProductDto> actual = productService.getProducts();

        // then
        verify(productRepository).findAll();
        verify(productMapper).toDto(List.of());
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    public void shouldReturnProductId_whenGetProductIdByName_givenProductName() {
        // given
        Optional<Product> productOptional = Optional.of(prepareFilledProduct(ID));
        String expected = ID;

        when(productRepository.findProductByName(anyString())).thenReturn(productOptional);

        // when
        String actual = productService.getProductIdByName(NAME);

        // then
        verify(productRepository).findProductByName(NAME);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldReturnNull_whenGetProductIdByName_givenProductName() {
        // given

        // when
        String actual = productService.getProductIdByName(NAME);

        // then
        verify(productRepository).findProductByName(NAME);
        assertThat(actual).isNull();
    }

    private Product prepareProduct(){
        Product product = new Product();
        product.setName(PRODUCT);
        product.setProteins(2f);
        product.setCarbs(3f);
        product.setFats(4f);
        return product;
    }

    private List<Product> prepapeProductList(){
        return List.of(prepareFilledProduct("id1"), prepareFilledProduct("id2"));
    }

    private Product prepareFilledProduct(String id){
        return new Product(id, NAME, 2f, 3f, 4f, 56f);
    }

    private List<ProductDto> prepapeProductDtoList(){
        return List.of(prepareFilledProductDto("id1"), prepareFilledProductDto("id2"));
    }

    private ProductDto prepareFilledProductDto(String id){
        return new ProductDto(id, NAME, 2f, 3f, 4f, 56f);
    }

}
