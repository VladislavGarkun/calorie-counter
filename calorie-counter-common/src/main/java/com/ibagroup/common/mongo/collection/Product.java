package com.ibagroup.common.mongo.collection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Document(value = "products")
public class Product {

    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    @NotNull(message = "Product name can't be null")
    @NotEmpty(message = "Product name can't be empty")
    private String name;

    @NotNull(message = "Proteins cannot be null")
    @Min(value = 0, message = "Proteins can't be less than 0")
    private Float proteins;

    @NotNull(message = "Carbs cannot be null")
    @Min(value = 0, message = "Carbs can't be less than 0")
    private Float carbs;

    @NotNull(message = "Fats cannot be null")
    @Min(value = 0, message = "Fats can't be less than 0")
    private Float fats;

    @NotNull(message = "Calories cannot be null")
    @Min(value = 0, message = "Calories can't be less than 0")
    private Float calories;

}
