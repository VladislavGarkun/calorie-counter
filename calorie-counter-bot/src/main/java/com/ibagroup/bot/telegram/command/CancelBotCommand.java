package com.ibagroup.bot.telegram.command;

import com.ibagroup.bot.command.Command;
import com.ibagroup.common.mongo.collection.State;
import com.ibagroup.common.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CancelBotCommand implements BotCommand {

    private final SessionService sessionService;

    @Override
    public Command getCommand() {
        return Command.CANCEL;
    }

    @Override
    public String execute(Update update) {
        Long chatId = update.getMessage().getChatId();
        sessionService.setBotState(chatId, State.DEFAULT);
        return "The current command was canceled";
    }

    @Override
    public String getDescription() {
        return "Cancel current command";
    }
}
