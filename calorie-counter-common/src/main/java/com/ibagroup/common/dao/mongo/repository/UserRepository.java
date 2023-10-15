package com.ibagroup.common.dao.mongo.repository;

import com.ibagroup.common.dao.mongo.collection.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

}
