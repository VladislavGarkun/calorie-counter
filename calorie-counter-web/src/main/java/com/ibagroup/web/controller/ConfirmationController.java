package com.ibagroup.web.controller;

import com.ibagroup.common.mongo.collection.Confirmation;
import com.ibagroup.common.service.ConfirmationService;
import com.ibagroup.common.service.SendMailService;
import com.ibagroup.common.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ConfirmationController {

    private final ConfirmationService confirmationService;
    private final SendMailService sendMailService;
    private final SessionService sessionService;

    @GetMapping(value = "/confirmation/{id}")
    public ResponseEntity confirmUser(@PathVariable String id) throws MessagingException {
        Optional<Confirmation> confirmationOptional = confirmationService.getConfirmationById(id);
        Confirmation confirmation = confirmationOptional.orElseThrow();
        sessionService.confirmUser(confirmation.getChatId());
        sendMailService.sendNotificationSuccessConfirmation(confirmation.getEmail());
        return ResponseEntity.ok("You successfully confirmed your email!");
    }

}
