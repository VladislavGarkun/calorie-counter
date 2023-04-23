package com.ibagroup.bot.telegram;

import com.ibagroup.bot.configuration.BotCommandFactory;
import com.ibagroup.bot.telegram.command.BotCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CalorieCounterBotFacade {

    private final BotCommandFactory botCommandFactory;

    protected String onUpdateReceived(Update update){
        BotCommand botCommand = botCommandFactory.getBotCommand(update);
        return botCommand.execute(update);
    }

}
