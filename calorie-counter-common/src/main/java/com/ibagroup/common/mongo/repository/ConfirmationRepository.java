package com.ibagroup.common.mongo.repository;

import com.ibagroup.common.mongo.collection.Confirmation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfirmationRepository extends MongoRepository<Confirmation, String> {



}
