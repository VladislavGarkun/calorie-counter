package com.ibagroup.common.mongo.repository;

import com.ibagroup.common.mongo.collection.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {



}
