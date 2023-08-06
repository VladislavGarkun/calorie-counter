package com.ibagroup.common.service;

import com.ibagroup.common.domain.dto.MealRegistrationDto;
import com.ibagroup.common.domain.mapper.MealRegistrationMapper;
import com.ibagroup.common.dao.mongo.collection.Meal;
import com.ibagroup.common.dao.mongo.repository.MealRepository;
import com.ibagroup.common.service.impl.MealServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MealServiceUnitTest {

    @InjectMocks
    private MealServiceImpl mealService;

    @Mock
    private MealRepository mealRepository;
    @Mock
    private MealRegistrationMapper mealRegistrationMapper;

    @Test
    public void shouldSaveMeal_whenCreateMeal_givenMealRegistrationDto(){
        // given
        MealRegistrationDto mealRegistrationDto = new MealRegistrationDto("productId", 1L, 100f, LocalDateTime.MAX);
        Meal meal = prepareMeal();

        when(mealRegistrationMapper.toEntity(any())).thenReturn(meal);
        when(mealRepository.insert(any(Meal.class))).thenReturn(meal);

        // when
        mealService.createMeal(mealRegistrationDto);

        // then
        verify(mealRegistrationMapper).toEntity(mealRegistrationDto);
        verify(mealRepository).insert(meal);
    }

    private Meal prepareMeal(){
        Meal meal = new Meal();
        meal.setProductId("productId");
        meal.setSessionId(1L);
        meal.setWeight(100f);
        meal.setMealDateTime(LocalDateTime.MAX);
        return meal;
    }

}
