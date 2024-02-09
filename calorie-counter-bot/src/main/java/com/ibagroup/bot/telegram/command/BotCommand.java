package com.ibagroup.bot.telegram.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ibagroup.bot.command.Command;
import com.ibagroup.common.dao.enums.State;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface BotCommand {

    Command getCommand();

    String execute(Update update) throws JsonProcessingException;

    String getDescription();

    default boolean isAnonymous(){
        return getCommand().isAnonymous();
    }

    default List<State> getStates(){
        return List.of();
    }

}
