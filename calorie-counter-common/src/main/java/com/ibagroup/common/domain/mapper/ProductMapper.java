package com.ibagroup.common.domain.mapper;

import com.ibagroup.common.domain.dto.ProductDto;
import com.ibagroup.common.dao.mongo.collection.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);

    List<ProductDto> toDto(List<Product> productList);

}
