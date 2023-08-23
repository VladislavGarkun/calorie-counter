package com.ibagroup.common.service.impl;

import com.ibagroup.common.dao.mongo.collection.Confirmation;
import com.ibagroup.common.dao.mongo.repository.ConfirmationRepository;
import com.ibagroup.common.service.ConfirmationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfirmationServiceImpl implements ConfirmationService {

    private final ConfirmationRepository confirmationRepository;

    public void createConfirmation(Confirmation confirmation){
        confirmationRepository.insert(confirmation);
    }

    public Optional<Confirmation> getConfirmationById(String id){
        return confirmationRepository.findById(id);
    }

}
