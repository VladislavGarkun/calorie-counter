package com.ibagroup.common.mongo.repository;

import com.ibagroup.common.mongo.collection.Session;
import com.ibagroup.common.service.SessionService;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SessionRepository extends MongoRepository<Session,String> {

    Optional<SessionService> findById(Long id);

}
