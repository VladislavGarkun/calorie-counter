package com.ibagroup.common.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class ProductDto {

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

}
