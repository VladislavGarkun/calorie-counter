package com.ibagroup.common.domain.mapper;

import com.ibagroup.common.domain.dto.ProductRegistrationDto;
import com.ibagroup.common.dao.mongo.collection.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRegistrationMapper {

    Product toEntity(ProductRegistrationDto productRegistrationDto);

}
