package com.ibagroup.common.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegistrationDto {

    @NotNull(message = "ProductId can't be null")
    private String productId;

    @NotNull(message = "SessionId can't be null")
    private Long sessionId;

    @NotNull(message = "Course weight can't be null")
    @Positive(message = "Weight can't be negative or zero")
    private Float weight;

    @NotNull(message = "Course date time can't be null")
    @PastOrPresent(message = "Course date time can't be future")
    private LocalDateTime courseDateTime;

}
