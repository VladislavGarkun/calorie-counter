package com.ibagroup.common.domain.mapper;

import com.ibagroup.common.domain.dto.MealDto;
import com.ibagroup.common.mongo.collection.Meal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MealMapper {

    MealDto toDto(Meal meal);

}
