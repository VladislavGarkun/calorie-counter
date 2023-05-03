package com.ibagroup.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendMailService {

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${registration.web.url}")
    private String url;

    @Async
    public void sendNotification(String uuid, String email) throws MailException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        String link = url + uuid;
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("giannis_34@mail.ru");
        helper.setTo(email);
        helper.setSubject("Please confirm your email for Calorie Counter Bot");
        helper.setText("""
        <html>
        Please click the link below to confirm your email for Calorie Counter Bot
        <br />        
        <a href="%s">%s</a>
        <br />
        Note, if you not requested this email, simply ignore.
        </html>
        """.formatted(link, link), true);
        javaMailSender.send(message);
    }

    @Async
    public void sendNotificationSuccessConfirmation(String email) throws MailException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("giannis_34@mail.ru");
        helper.setTo(email);
        helper.setSubject("You confirmed your email for Calorie Counter Bot");
        helper.setText("""
        <html>
        You successfully confirmed your email for Calorie Counter Bot
        </html>
        """, true);
        javaMailSender.send(message);
    }

}
