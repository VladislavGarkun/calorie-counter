package com.ibagroup.common.dao.mongo.collection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Setter
@Getter
@Document(value = "meals")
public class Meal {

    @Id
    @Setter(AccessLevel.NONE)
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
