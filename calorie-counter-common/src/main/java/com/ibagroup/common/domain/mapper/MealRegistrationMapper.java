package com.ibagroup.common.domain.mapper;

import com.ibagroup.common.domain.dto.MealRegistrationDto;
import com.ibagroup.common.dao.mongo.collection.Meal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MealRegistrationMapper {

    Meal toEntity(MealRegistrationDto mealRegistrationDto);

}
