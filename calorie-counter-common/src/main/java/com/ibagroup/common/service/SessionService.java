package com.ibagroup.common.service;

import com.ibagroup.common.mongo.collection.Session;
import com.ibagroup.common.mongo.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    public boolean isChatNew(Long id){
        return sessionRepository.findById(id).isEmpty();
    }

    public void initChat(Long id){
        sessionRepository.insert(new Session(id));
    }

}
