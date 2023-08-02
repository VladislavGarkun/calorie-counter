package com.ibagroup.common.service;

import com.ibagroup.common.mongo.collection.Confirmation;
import com.ibagroup.common.mongo.repository.ConfirmationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfirmationService {

    private final ConfirmationRepository confirmationRepository;

    public void createConfirmation(Confirmation confirmation){
        confirmationRepository.insert(confirmation);
    }

    public Optional<Confirmation> getConfirmationById(String id){
        return confirmationRepository.findById(id);
    }

}
