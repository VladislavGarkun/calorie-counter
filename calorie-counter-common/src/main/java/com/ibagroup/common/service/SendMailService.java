package com.ibagroup.common.service;

import javax.mail.MessagingException;

public interface SendMailService {

    void sendNotification(String uuid, String email) throws MessagingException;

    void sendNotificationSuccessConfirmation(String email) throws MessagingException;

}
