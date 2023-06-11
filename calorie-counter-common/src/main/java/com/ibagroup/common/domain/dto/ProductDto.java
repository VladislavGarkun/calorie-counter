package com.ibagroup.common.domain.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @Setter(AccessLevel.NONE)
    private String id;

    @NotNull(message = "Product name can't be null")
    @NotEmpty(message = "Product name can't be empty")
    private String name;

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

}
