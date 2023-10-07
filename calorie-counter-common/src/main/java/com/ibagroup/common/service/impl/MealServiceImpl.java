package com.ibagroup.common.service.impl;

import com.ibagroup.common.dao.mongo.collection.Meal;
import com.ibagroup.common.dao.mongo.repository.MealRepository;
import com.ibagroup.common.domain.dto.MealDto;
import com.ibagroup.common.domain.dto.MealRegistrationDto;
import com.ibagroup.common.domain.mapper.MealMapper;
import com.ibagroup.common.domain.mapper.MealRegistrationMapper;
import com.ibagroup.common.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealMapper mealMapper;
    private final MealRepository mealRepository;
    private final MealRegistrationMapper mealRegistrationMapper;

    public void createMeal(MealRegistrationDto mealRegistrationDto) {
        Meal meal = mealRegistrationMapper.toEntity(mealRegistrationDto);
        mealRepository.insert(meal);
    }

    public List<MealDto> getProductIdsByChatId(Long chatId) {
        List<Meal> meals = mealRepository.findAllBySessionId(chatId);
        return meals
                .stream()
                .filter(meal -> meal.getMealDateTime().toLocalDate().isEqual(LocalDate.now()))
                .map(mealMapper::toDto)
                .collect(Collectors.toList());
    }

    public boolean isMealNew(String productId){
        return mealRepository.findAllByProductId(productId)
                .stream()
                .noneMatch(meal -> meal.getMealDateTime().toLocalDate().isEqual(LocalDate.now()));
    }

    private Meal getMealByProductId(String productId){
        return mealRepository.findAllByProductId(productId)
                .stream()
                .filter(meal -> meal.getMealDateTime().toLocalDate().isEqual(LocalDate.now()))
                .collect(Collectors.toList())
                .get(0);
    }

    public void updateMeal(String productId, Float weight){
        Meal meal = getMealByProductId(productId);
        meal.setWeight(meal.getWeight() + weight);
        mealRepository.save(meal);
    }
}
