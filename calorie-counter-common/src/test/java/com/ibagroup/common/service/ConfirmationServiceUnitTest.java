package com.ibagroup.common.service;

import com.ibagroup.common.dao.mongo.collection.Confirmation;
import com.ibagroup.common.dao.mongo.repository.ConfirmationRepository;
import com.ibagroup.common.service.impl.ConfirmationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConfirmationServiceUnitTest {

    @InjectMocks
    private ConfirmationServiceImpl confirmationService;

    @Mock
    private ConfirmationRepository confirmationRepository;

    @Test
    public void whenCreateConfirmation_givenConfirmation(){
        // given
        Confirmation confirmation = prepareConfirmation();

        // when
        confirmationService.createConfirmation(confirmation);

        // then
        verify(confirmationRepository).insert(confirmation);
    }

    @Test
    public void shouldReturnConfirmation_whenGetConfirmationById_givenConfirmationId(){
        // given
        Confirmation confirmation = prepareConfirmation();
        String id = confirmation.getId();
        Optional<Confirmation> expected = Optional.of(confirmation);

        when(confirmationRepository.findById(any())).thenReturn(Optional.of(confirmation));

        // when
        Optional<Confirmation> actual = confirmationService.getConfirmationById(id);

        // then
        assertThat(actual).isEqualTo(expected);
        verify(confirmationRepository).findById(id);
    }

    @Test
    public void shouldReturnEmptyOptional_whenGetConfirmationById_givenConfirmationId(){
        // given
        Confirmation confirmation = prepareConfirmation();
        String id = confirmation.getId();
        Optional<Confirmation> expected = Optional.empty();

        when(confirmationRepository.findById(any())).thenReturn(Optional.empty());

        // when
        Optional<Confirmation> actual = confirmationService.getConfirmationById(id);

        // then
        assertThat(actual).isEqualTo(expected);
        verify(confirmationRepository).findById(id);
    }

    private Confirmation prepareConfirmation(){
        return new Confirmation(UUID.randomUUID().toString(), 1L, LocalDateTime.MAX, "randommail@gmail.com");
    }

}
