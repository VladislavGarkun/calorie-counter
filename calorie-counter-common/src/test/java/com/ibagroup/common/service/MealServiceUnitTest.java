package com.ibagroup.common.service;

import com.ibagroup.common.domain.dto.MealDto;
import com.ibagroup.common.domain.dto.MealRegistrationDto;
import com.ibagroup.common.domain.mapper.MealMapper;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MealServiceUnitTest {

    private static final LocalDateTime dateTime = LocalDateTime.now();

    @InjectMocks
    private MealServiceImpl mealService;

    @Mock
    private MealMapper mealMapper;
    @Mock
    private MealRepository mealRepository;
    @Mock
    private MealRegistrationMapper mealRegistrationMapper;

    @Test
    public void shouldSaveMeal_whenCreateMeal_givenMealRegistrationDto(){
        // given
        MealRegistrationDto mealRegistrationDto = new MealRegistrationDto("productId", 1L, 100f, LocalDateTime.MAX);
        Meal meal = prepareMeal("productId", LocalDateTime.MAX);

        when(mealRegistrationMapper.toEntity(any())).thenReturn(meal);
        when(mealRepository.insert(any(Meal.class))).thenReturn(meal);

        // when
        mealService.createMeal(mealRegistrationDto);

        // then
        verify(mealRegistrationMapper).toEntity(mealRegistrationDto);
        verify(mealRepository).insert(meal);
    }

    @Test
    public void shouldReturnMealDtoList_whenGetMealsByChatId_givenChatId(){
        // given
        List<Meal> meals = prepareMeals();
        MealDto mealDto = prepareMealDto();
        List<MealDto> expected = List.of(mealDto);

        when(mealRepository.findAllBySessionId(any())).thenReturn(meals);
        when(mealMapper.toDto(any())).thenReturn(mealDto);

        // when
        List<MealDto> actual = mealService.getMealsByChatId(1L);

        // then
        assertThat(actual).isEqualTo(expected);
        verify(mealRepository).findAllBySessionId(1L);
        verify(mealMapper).toDto(meals.get(0));
    }

    private List<Meal> prepareMeals(){
        return List.of(prepareMeal("productId1", dateTime), prepareMeal("productId2", dateTime.minusDays(1)));
    }

    private Meal prepareMeal(String id, LocalDateTime mealDateTime){
        Meal meal = new Meal();
        meal.setProductId(id);
        meal.setSessionId(1L);
        meal.setWeight(100f);
        meal.setMealDateTime(mealDateTime);
        return meal;
    }

    private MealDto prepareMealDto(){
        MealDto mealDto = new MealDto();
        mealDto.setProductId("productId1");
        mealDto.setSessionId(1L);
        mealDto.setWeight(100f);
        mealDto.setMealDateTime(dateTime);
        return mealDto;
    }

}
