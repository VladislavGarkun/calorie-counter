package com.ibagroup.bot.configuration;

import com.ibagroup.bot.command.Command;
import com.ibagroup.bot.telegram.command.BotCommand;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@Component
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "bot")
public class BotConfiguration {

    private String name;

    private String accessToken;

    private final ListableBeanFactory beanFactory;

    private Map<Command, BotCommand> botCommands = new EnumMap<>(Command.class);

    private final MessageConfiguration messageConfiguration;

    @PostConstruct
    public void init(){
        botCommands = beanFactory.getBeansOfType(BotCommand.class).values().stream()
                .collect(Collectors.toMap(BotCommand::getCommand, Function.identity()));

        String helpMessage = Stream.of(Command.values())
                .map(command -> botCommands.get(command))
                .map(botCommand -> "/" + botCommand.getCommand().name().toLowerCase() + "--" + botCommand.getDescription() + (botCommand.isAnonymous() ? "" : "(<i>requires authentication</i>)"))
                .collect(Collectors.joining("\n"));
        messageConfiguration.setHelpMessage(helpMessage);
    }

    public BotCommand getBotCommand(Command command){
        return botCommands.get(command);
    }

}
