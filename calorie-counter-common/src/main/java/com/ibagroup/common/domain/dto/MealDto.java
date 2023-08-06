package com.ibagroup.common.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
public class MealDto {

    private String id;

    @NotNull(message = "ProductId can't be null")
    private String productId;

    @NotNull(message = "SessionId can't be null")
    private Long sessionId;

    @NotNull(message = "Meal weight can't be null")
    @Positive(message = "Weight can't be negative or zero")
    private Float weight;

    @NotNull(message = "Meal date time can't be null")
    @PastOrPresent(message = "Meal date time can't be future")
    private LocalDateTime mealDateTime;

}
