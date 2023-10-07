package com.ibagroup.common.service.impl;

import com.ibagroup.common.domain.dto.MealDto;
import com.ibagroup.common.domain.dto.MealRegistrationDto;
import com.ibagroup.common.domain.mapper.MealMapper;
import com.ibagroup.common.domain.mapper.MealRegistrationMapper;
import com.ibagroup.common.dao.mongo.collection.Meal;
import com.ibagroup.common.dao.mongo.repository.MealRepository;
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

    public List<MealDto> getMealsByChatId(Long chatId) {
        List<Meal> meals = mealRepository.findAllBySessionId(chatId);
        return meals
                .stream()
                .filter(meal -> meal.getMealDateTime().toLocalDate().isEqual(LocalDate.now()))
                .map(mealMapper::toDto)
                .collect(Collectors.toList());
    }
}
