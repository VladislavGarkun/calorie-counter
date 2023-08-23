package com.ibagroup.common.dao.mongo.repository;

import com.ibagroup.common.dao.mongo.collection.Confirmation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfirmationRepository extends MongoRepository<Confirmation, String> {



}
