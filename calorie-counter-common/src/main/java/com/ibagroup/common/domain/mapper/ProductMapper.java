package com.ibagroup.common.domain.mapper;

import com.ibagroup.common.domain.dto.ProductDto;
import com.ibagroup.common.mongo.collection.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);

}
