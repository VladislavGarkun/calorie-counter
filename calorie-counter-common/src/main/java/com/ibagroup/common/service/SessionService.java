package com.ibagroup.common.service;

import com.ibagroup.common.dao.enums.State;
import com.ibagroup.common.dao.mongo.collection.Session;

public interface SessionService {

    boolean isChatNew(Long id);

    void initChat(Long id);

    boolean isUserConfirmed(Long id);

    Session getSession(Long id);

    State getBotState(Long id);

    void setBotState(Long id, State state);

    void setName(Long id, String name);

    void setEmail(Long id, String email);

    void confirmUser(Long id);

}
