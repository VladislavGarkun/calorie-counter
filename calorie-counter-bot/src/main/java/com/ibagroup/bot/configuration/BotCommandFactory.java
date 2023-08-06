package com.ibagroup.bot.configuration;

import com.ibagroup.bot.command.Command;
import com.ibagroup.bot.telegram.command.BotCommand;
import com.ibagroup.common.mongo.collection.State;
import com.ibagroup.common.service.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
@RequiredArgsConstructor
public class BotCommandFactory {

    private final SessionService serviceService;
    private final BotConfiguration botConfiguration;

    public BotCommand getBotCommand(Update update){
        Long chatId = update.getMessage().getChatId();

        if(serviceService.isChatNew(chatId)){
            serviceService.initChat(chatId);
            return botConfiguration.getBotCommand(Command.HELP);
        }

        String inputText = update.getMessage().getText();
        State activeState = serviceService.getBotState(chatId);

        BotCommand commandToRun;
        if(activeState  == State.DEFAULT){
            commandToRun = botConfiguration.getBotCommand(inputText);
        } else {
            commandToRun = botConfiguration.getBotCommand(activeState);
            BotCommand userCommand = botConfiguration.getBotCommand(inputText);

            if(userCommand != null && userCommand.getCommand() == Command.CANCEL){
                commandToRun = userCommand;
            }
        }

        return commandToRun == null ? botConfiguration.getBotCommand(Command.HELP) : commandToRun;
    }

}
