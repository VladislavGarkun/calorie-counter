package com.ibagroup.common.domain.mapper;

import com.ibagroup.common.domain.dto.EatenMealDto;
import com.ibagroup.common.domain.dto.MealDto;
import com.ibagroup.common.domain.dto.ProductDto;

import java.util.List;
import java.util.Map;

public interface EatenMealMapper {

    List<EatenMealDto> toDto(List<MealDto> mealDtoList, Map<String, ProductDto> productDtoMap);

}
