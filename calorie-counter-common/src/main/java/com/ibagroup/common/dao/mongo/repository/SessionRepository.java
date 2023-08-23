package com.ibagroup.common.dao.mongo.repository;

import com.ibagroup.common.dao.mongo.collection.Session;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepository extends MongoRepository<Session, Long> {



}
