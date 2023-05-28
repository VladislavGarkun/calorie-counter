package com.ibagroup.common.mongo.repository;

import com.ibagroup.common.mongo.collection.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findProductByName(String name);

}
