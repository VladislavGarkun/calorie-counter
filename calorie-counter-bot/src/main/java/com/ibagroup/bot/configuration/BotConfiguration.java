package com.ibagroup.bot.configuration;

import com.ibagroup.bot.command.Command;
import com.ibagroup.bot.telegram.command.BotCommand;
import com.ibagroup.common.dao.enums.State;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.HashMap;
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

    private Map<Command, BotCommand> botCommands = new EnumMap<>(Command.class);
    private Map<State, BotCommand> botCommandsByState = new EnumMap<>(State.class);
    private Map<String, BotCommand> botCommandsByString = new HashMap<>();

    private final ListableBeanFactory beanFactory;
    private final MessageConfiguration messageConfiguration;

    @PostConstruct
    public void init(){
        botCommands = beanFactory.getBeansOfType(BotCommand.class)
                .values()
                .stream()
                .collect(Collectors.toMap(BotCommand::getCommand, Function.identity()));

        botCommandsByState = beanFactory.getBeansOfType(BotCommand.class)
                .values()
                .stream()
                .flatMap(botCommand -> botCommand.getStates().stream().map(state -> Map.entry(state, botCommand)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        botCommandsByString = beanFactory.getBeansOfType(BotCommand.class).values().stream().collect(Collectors.toMap(botCommand -> "/" + botCommand.getCommand().name().toLowerCase(), Function.identity()));

        String helpMessage = Stream.of(Command.values())
                .map(command -> botCommands.get(command))
                .map(botCommand -> "/" + botCommand.getCommand().name().toLowerCase() + " -- " + botCommand.getDescription() + (botCommand.isAnonymous() ? "" : " (<i>requires authentication</i>)"))
                .collect(Collectors.joining("\n"));
        messageConfiguration.setHelpMessage(helpMessage);
    }

    public BotCommand getBotCommand(Command command){
        return botCommands.get(command);
    }

    public BotCommand getBotCommand(String command){
        return botCommandsByString.get(command);
    }

    public BotCommand getBotCommand(State state){
        return botCommandsByState.get(state);
    }

}
