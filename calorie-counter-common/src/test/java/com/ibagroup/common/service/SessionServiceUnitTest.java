package com.ibagroup.common.service;

import com.ibagroup.common.dao.mongo.collection.Session;
import com.ibagroup.common.dao.mongo.repository.SessionRepository;
import com.ibagroup.common.service.impl.SessionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SessionServiceUnitTest {

    @InjectMocks
    private SessionServiceImpl sessionService;

    @Mock
    SessionRepository sessionRepository;

    @Test
    public void shouldReturnTrue_whenIsChatNew_givenChatId(){
        // given
        Long chatId = 1L;

        // when
        boolean actual = sessionService.isChatNew(chatId);

        // then
        assertThat(actual).isTrue();
        verify(sessionRepository).findById(chatId);
    }

    @Test
    public void shouldReturnFalse_whenIsChatNew_givenChatId(){
        // given
        Long chatId = 1L;

        when(sessionRepository.findById(any())).thenReturn(Optional.of(new Session(1L)));

        // when
        boolean actual = sessionService.isChatNew(chatId);

        // then
        assertThat(actual).isFalse();
        verify(sessionRepository).findById(chatId);
    }

}
