package com.ibagroup.common.mongo.repository;

import com.ibagroup.common.mongo.collection.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MealRepository extends MongoRepository<Meal, String> {



}
