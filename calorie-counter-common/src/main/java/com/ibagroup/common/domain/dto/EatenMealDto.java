package com.ibagroup.common.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class EatenMealDto {

    @NotNull(message = "Product name can't be null")
    @NotEmpty(message = "Product name can't be empty")
    private String name;

    @NotNull(message = "Meal weight can't be null")
    @Positive(message = "Weight can't be negative or zero")
    private Float weight;

    @NotNull(message = "Proteins cannot be null")
    @PositiveOrZero(message = "Proteins can't be less than 0")
    private Float proteins;

    @NotNull(message = "Carbs cannot be null")
    @PositiveOrZero(message = "Carbs can't be less than 0")
    private Float carbs;

    @NotNull(message = "Fats cannot be null")
    @PositiveOrZero(message = "Fats can't be less than 0")
    private Float fats;

    @NotNull(message = "Calories cannot be null")
    @Min(value = 0, message = "Calories can't be less than 0")
    private Float calories;

    @NotNull(message = "Meal date time can't be null")
    @PastOrPresent(message = "Meal date time can't be future")
    private LocalDateTime mealDateTime;

}
