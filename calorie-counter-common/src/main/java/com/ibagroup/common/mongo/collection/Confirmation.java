package com.ibagroup.common.mongo.collection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "confirmation")
@RequiredArgsConstructor
public class Confirmation {

    @Id
    @Setter(AccessLevel.NONE)
    private final String id;

    @NonNull
    private final Long chatId;

    @Field(targetType = FieldType.DATE_TIME)
    @NonNull
    private final LocalDateTime creationDateTime;

    @Email(message = "Email must be correct")
    @NonNull
    private final String email;

}
