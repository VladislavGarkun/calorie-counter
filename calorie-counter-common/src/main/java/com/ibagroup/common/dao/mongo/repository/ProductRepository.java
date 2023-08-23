package com.ibagroup.common.dao.mongo.repository;

import com.ibagroup.common.dao.mongo.collection.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findProductByName(String name);

    List<Product> findProductsByIdIn(List<String> productIds);

}
