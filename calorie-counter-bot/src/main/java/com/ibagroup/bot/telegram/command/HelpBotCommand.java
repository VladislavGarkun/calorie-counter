package com.ibagroup.bot.telegram.command;

import com.ibagroup.bot.command.Command;
import com.ibagroup.bot.configuration.MessageConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@AllArgsConstructor
public class HelpBotCommand implements BotCommand{

    private final MessageConfiguration messageConfiguration;

    @Override
    public Command getCommand() {
        return Command.HELP;
    }

    @Override
    public String execute(Update update) {
        return messageConfiguration.getHelpMessage();
    }

    @Override
    public String getDescription() {
        return "Helps the bot";
    }
}
