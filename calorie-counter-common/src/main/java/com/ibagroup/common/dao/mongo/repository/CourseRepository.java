package com.ibagroup.common.dao.mongo.repository;

import com.ibagroup.common.dao.mongo.collection.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {

    List<Course> findAllBySessionId(Long chatId);

    List<Course> findAllByProductId(String productId);

}
