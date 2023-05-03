package com.ibagroup.common.service;

import com.ibagroup.common.mongo.collection.Session;
import com.ibagroup.common.mongo.collection.State;
import com.ibagroup.common.mongo.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Session getSession(Long id){
        return sessionRepository.findById(id).orElseThrow();
    }

    public State getBotState(Long id){
        Optional<Session> sessionOptional = sessionRepository.findById(id);
        return sessionOptional.map(Session::getState).orElseThrow();
    }

    public void setBotState(Long id, State state){
        Session session = sessionRepository.findById(id).orElseThrow();
        session.setState(state);
        sessionRepository.save(session);
    }

    public void setName(Long id, String name) {
        Session session = sessionRepository.findById(id).orElseThrow();
        session.setName(name);
        sessionRepository.save(session);
    }

    public void setEmail(Long id, String email) {
        Session session = sessionRepository.findById(id).orElseThrow();
        session.setName(email);
        sessionRepository.save(session);
    }

    public void confirmUser(Long id){
        Session session = sessionRepository.findById(id).orElseThrow();
        session.setConfirmed(true);
        sessionRepository.save(session);
    }

}
