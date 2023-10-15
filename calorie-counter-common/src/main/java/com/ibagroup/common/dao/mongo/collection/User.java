package com.ibagroup.common.dao.mongo.collection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(value = "users")
public class User {

    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    private String username;

    private String password;

    private String role;

}
