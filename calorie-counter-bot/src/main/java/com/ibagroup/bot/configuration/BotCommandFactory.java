package com.ibagroup.bot.configuration;

import com.ibagroup.bot.command.Command;
import com.ibagroup.bot.telegram.command.BotCommand;
import com.ibagroup.common.service.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
@RequiredArgsConstructor
public class BotCommandFactory {

    private final BotConfiguration botConfiguration;

    private final SessionService serviceService;

    public BotCommand getBotCommand(Update update){
        Long chatId = update.getMessage().getChatId();

        if(serviceService.isChatNew(chatId)){
            serviceService.initChat(chatId);
            return botConfiguration.getBotCommand(Command.HELP);
        }

        return botConfiguration.getBotCommand(Command.HELP);
    }

}
