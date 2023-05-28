package com.ibagroup.common.service;

import com.ibagroup.common.domain.dto.MealRegistrationDto;
import com.ibagroup.common.domain.mapper.MealRegistrationMapper;
import com.ibagroup.common.mongo.collection.Meal;
import com.ibagroup.common.mongo.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final MealRegistrationMapper mealRegistrationMapper;

    public void createMeal(MealRegistrationDto mealRegistrationDto) {
        Meal meal = mealRegistrationMapper.toEntity(mealRegistrationDto);
        mealRepository.insert(meal);
    }

}
