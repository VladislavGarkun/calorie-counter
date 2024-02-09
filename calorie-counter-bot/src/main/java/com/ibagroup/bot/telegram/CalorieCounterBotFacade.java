package com.ibagroup.bot.telegram;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ibagroup.bot.configuration.BotCommandFactory;
import com.ibagroup.bot.telegram.command.BotCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CalorieCounterBotFacade {

    private final BotCommandFactory botCommandFactory;

    protected String onUpdateReceived(Update update) throws JsonProcessingException {
        BotCommand botCommand = botCommandFactory.getBotCommand(update);
        return botCommand.execute(update);
    }

}
