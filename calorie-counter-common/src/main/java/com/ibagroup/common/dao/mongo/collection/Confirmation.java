package com.ibagroup.common.dao.mongo.collection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "confirmation")
@RequiredArgsConstructor
public class Confirmation {

    @Id
    @Setter(AccessLevel.NONE)
    private final String id;

    @NotNull(message = "Chat id can't be null")
    private final Long chatId;

    @Field(targetType = FieldType.DATE_TIME)
    @NotNull(message = "Creation date time can't be null")
    private final LocalDateTime creationDateTime;

    @Email(message = "Email must be correct")
    @NotNull(message = "Email can't be null")
    private final String email;

}
