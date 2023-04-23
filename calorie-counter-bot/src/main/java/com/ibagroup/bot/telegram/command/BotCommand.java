package com.ibagroup.bot.telegram.command;

import com.ibagroup.bot.command.Command;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotCommand {

    Command getCommand();

    default boolean isAnonymous(){
        return getCommand().isAnonymous();
    }

    String execute(Update update);

    String getDescription();

}
