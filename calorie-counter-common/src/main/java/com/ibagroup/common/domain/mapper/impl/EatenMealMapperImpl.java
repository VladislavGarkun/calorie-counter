package com.ibagroup.common.domain.mapper.impl;

import com.ibagroup.common.domain.dto.EatenMealDto;
import com.ibagroup.common.domain.dto.MealDto;
import com.ibagroup.common.domain.dto.ProductDto;
import com.ibagroup.common.domain.mapper.EatenMealMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class EatenMealMapperImpl implements EatenMealMapper {

    @Override
    public List<EatenMealDto> toDto(List<MealDto> mealDtoList, Map<String, ProductDto> productDtoMap) {
        List<EatenMealDto> eatenMealDtoList = new ArrayList<>();

        for(MealDto mealDto : mealDtoList){
            EatenMealDto eatenMealDto = new EatenMealDto();
            ProductDto productDto = productDtoMap.get(mealDto.getProductId());

            eatenMealDto.setName(productDto.getName());
            eatenMealDto.setWeight(mealDto.getWeight());
            eatenMealDto.setProteins(productDto.getProteins() * mealDto.getWeight() / 100);
            eatenMealDto.setCarbs(productDto.getCarbs() * mealDto.getWeight() / 100);
            eatenMealDto.setFats(productDto.getFats() * mealDto.getWeight() / 100);
            eatenMealDto.setCalories(productDto.getCalories() * mealDto.getWeight() / 100);
            eatenMealDto.setMealDateTime(mealDto.getMealDateTime());

            eatenMealDtoList.add(eatenMealDto);
        }

        return eatenMealDtoList;
    }

}
