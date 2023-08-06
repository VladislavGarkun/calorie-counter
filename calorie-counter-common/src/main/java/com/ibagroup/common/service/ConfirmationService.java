package com.ibagroup.common.service;

import com.ibagroup.common.dao.mongo.collection.Confirmation;

import java.util.Optional;

public interface ConfirmationService {

    void createConfirmation(Confirmation confirmation);

    Optional<Confirmation> getConfirmationById(String id);

}
