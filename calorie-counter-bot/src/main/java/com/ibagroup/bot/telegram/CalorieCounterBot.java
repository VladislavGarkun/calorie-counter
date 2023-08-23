package com.ibagroup.bot.telegram;

import com.ibagroup.bot.configuration.BotConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CalorieCounterBot extends TelegramLongPollingBot {

    private final BotConfiguration botConfiguration;
    private final CalorieCounterBotFacade calorieCounterBotFacade;

    @Override
    public void onUpdateReceived(Update update) {
        String message = calorieCounterBotFacade.onUpdateReceived(update);
        sendMessage(update, message);
    }

    @Override
    public String getBotUsername() {
        return botConfiguration.getName();
    }

    @Override
    public String getBotToken() {
        return botConfiguration.getAccessToken();
    }

    private void sendMessage(Update update, String message){
        if(!update.hasMessage()){
            return;
        }

        Long chatId = update.getMessage().getChatId();
        SendMessage.SendMessageBuilder sendMessageBuilder = SendMessage.builder();
        sendMessageBuilder.chatId(chatId).parseMode(ParseMode.HTML).text(message);
        try {
            execute(sendMessageBuilder.build());
        } catch (TelegramApiException telegramApiException) {
            log.error("Failed to send message '{}' to chatId={} because of error {}", message, chatId, telegramApiException);
        }
    }

}
