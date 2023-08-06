package com.ibagroup.bot.telegram.command;

import com.ibagroup.bot.command.Command;
import com.ibagroup.common.dao.enums.State;
import com.ibagroup.common.dao.mongo.collection.Confirmation;
import com.ibagroup.common.dao.mongo.collection.Session;
import com.ibagroup.common.service.ConfirmationService;
import com.ibagroup.common.service.SendMailService;
import com.ibagroup.common.service.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticateBotCommand implements BotCommand {

    @Value("${allowed.mail.domains}")
    private Set<String> domains;

    private final SessionService sessionService;
    private final SendMailService sendMailService;
    private final ConfirmationService confirmationService;

    @Override
    public Command getCommand() {
        return Command.AUTHENTICATE;
    }

    @Override
    public List<State> getStates() {
        return List.of(State.AUTHENTICATION_ENTER_NAME, State.AUTHENTICATION_ENTER_EMAIL);
    }

    @Override
    public String execute(Update update) {
        Long chatId = update.getMessage().getChatId();
        Session session = sessionService.getSession(chatId);
        if(session.isConfirmed()){
            return "You have already confirmed your mail";
        }

        return switch (session.getState()){
            case DEFAULT: {
                sessionService.setBotState(chatId, State.AUTHENTICATION_ENTER_NAME);
                yield "Please enter your name: ";
            }
            case AUTHENTICATION_ENTER_NAME: {
                String name = update.getMessage().getText();
                if(name != null && !name.isEmpty()) {
                    sessionService.setBotState(chatId, State.AUTHENTICATION_ENTER_EMAIL);
                    sessionService.setName(chatId, name);
                    yield "Please enter your email: ";
                } else {
                    yield "You must enter your name or cancel to exit to the top menu";
                }
            }
            case AUTHENTICATION_ENTER_EMAIL: {
                String email = update.getMessage().getText();
                if(email != null && email.indexOf("@") > 0 && domains.contains(email.substring(email.indexOf("@") + 1))){
                    try {
                        sendNotification(chatId, email);
                    } catch (MessagingException e) {
                        log.error("Unable to send email", e);
                        yield "Error occurred while sending email. Please repeat later.";
                    }

                    sessionService.setEmail(chatId, email);
                    sessionService.setBotState(chatId, State.DEFAULT);
                    yield "You should receive an email soon. Please click the link to confirm your identity. The link will expire after 24 hours.";
                } else {
                    yield "You must enter your email (valid domains are %s) or cancel the command to exit to the top menu".formatted(domains);
                }
            }
            default: {
                sessionService.setBotState(chatId, State.DEFAULT);
                yield "Command cancelled";
            }
        };
    }

    @Override
    public String getDescription() {
        return "Authenticate user";
    }

    private void sendNotification(Long chatId, String email) throws MessagingException {
        UUID uuid = UUID.randomUUID();
        Confirmation confirmation = new Confirmation(uuid.toString(), chatId, LocalDateTime.now(), email);
        confirmationService.createConfirmation(confirmation);
        sendMailService.sendNotification(uuid.toString(), email);
    }

}
