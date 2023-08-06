package com.ibagroup.common.mongo.repository;

import com.ibagroup.common.mongo.collection.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MealRepository extends MongoRepository<Meal, String> {

    List<Meal> findAllBySessionId(Long chatId);

}
