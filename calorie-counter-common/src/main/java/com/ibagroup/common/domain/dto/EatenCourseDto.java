package com.ibagroup.common.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class EatenCourseDto {

    @NotNull(message = "Product name can't be null")
    @NotEmpty(message = "Product name can't be empty")
    private String name;

    @NotNull(message = "Course weight can't be null")
    @Positive(message = "Weight can't be negative or zero")
    private Float weight;

    @NotNull(message = "Proteins can't be null")
    @PositiveOrZero(message = "Proteins can't be less than 0")
    private Float proteins;

    @NotNull(message = "Carbs can't be null")
    @PositiveOrZero(message = "Carbs can't be less than 0")
    private Float carbs;

    @NotNull(message = "Fats can't be null")
    @PositiveOrZero(message = "Fats can't be less than 0")
    private Float fats;

    @NotNull(message = "Calories can't be null")
    @Min(value = 0, message = "Calories can't be less than 0")
    private Float calories;

    @NotNull(message = "Course date time can't be null")
    @PastOrPresent(message = "Course date time can't be future")
    private LocalDateTime courseDateTime;

}
