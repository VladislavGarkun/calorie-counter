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

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "sessions")
@RequiredArgsConstructor
public class Session {

    @Id
    @Setter(AccessLevel.NONE)
    @NonNull
    private Long id;

    @Field(targetType = FieldType.STRING)
    private State state = State.DEFAULT;

    @Field(targetType = FieldType.DATE_TIME)
    private LocalDateTime confirmedTime;

    @Field(targetType = FieldType.BOOLEAN)
    private boolean isConfirmed;

    @Field(targetType = FieldType.STRING)
    private String email;

    @Field(targetType = FieldType.STRING)
    private String name;

}
