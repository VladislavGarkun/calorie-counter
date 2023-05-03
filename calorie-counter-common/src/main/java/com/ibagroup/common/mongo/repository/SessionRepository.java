package com.ibagroup.common.mongo.repository;

import com.ibagroup.common.mongo.collection.Session;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepository extends MongoRepository<Session, Long> {



}
