package com.ibagroup.common.service;

import com.ibagroup.common.domain.dto.MealDto;
import com.ibagroup.common.domain.dto.MealRegistrationDto;

import java.util.List;

public interface MealService {

    void createMeal(MealRegistrationDto mealRegistrationDto);

    List<MealDto> getProductIdsByChatId(Long chatId);

    boolean isMealNew(String productId);

    void updateMeal(String productId, Float weight);

}
