package com.ibagroup.bot.telegram.command;

import com.ibagroup.bot.command.Command;
import com.ibagroup.common.dao.enums.State;
import com.ibagroup.common.dao.mongo.collection.Session;
import com.ibagroup.common.domain.dto.MealRegistrationDto;
import com.ibagroup.common.service.MealService;
import com.ibagroup.common.service.ProductService;
import com.ibagroup.common.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class AddMealBotCommand implements BotCommand {

    private final static String NUMBER_PATTERN = "[0-9]+\\.?[0-9]*";

    private final MealService mealService;
    private final SessionService sessionService;
    private final ProductService productService;

    private MealRegistrationDto mealRegistrationDto = new MealRegistrationDto();

    @Override
    public Command getCommand() {
        return Command.ADD_MEAL;
    }

    @Override
    public List<State> getStates(){
        return List.of(State.ENTER_PRODUCT_NAME, State.ENTER_PRODUCT_WEIGHT);
    }

    @Override
    public String execute(Update update) {
        Long chatId = update.getMessage().getChatId();
        if(!sessionService.isUserConfirmed(chatId)) {
            return "You need to authenticate to use this command";
        }
        Session session = sessionService.getSession(chatId);

        return switch (session.getState()){
            case DEFAULT:{
                sessionService.setBotState(chatId, State.ENTER_PRODUCT_NAME);
                yield "Please enter product name: ";
            }
            case ENTER_PRODUCT_NAME: {
                String name = update.getMessage().getText();
                String productId = productService.getProductIdByName(name);
                if (productId != null){
                    mealRegistrationDto.setProductId(productId);
                    sessionService.setBotState(chatId, State.ENTER_PRODUCT_WEIGHT);
                    yield "Please enter meal weight: ";
                } else {
                    yield "Product with this name doesn't exist. Please try again: ";
                }
            }
            case ENTER_PRODUCT_WEIGHT: {
                String weight = update.getMessage().getText();
                if (Pattern.matches(NUMBER_PATTERN, weight) && Float.valueOf(weight) > 0) {
                    mealRegistrationDto.setWeight(Float.valueOf(weight));
                    mealRegistrationDto.setMealDateTime(LocalDateTime.now());
                    mealRegistrationDto.setSessionId(chatId);
                    mealService.createMeal(mealRegistrationDto);
                    sessionService.setBotState(chatId, State.DEFAULT);
                    yield "Meal was successfully added";
                } else {
                   yield "Please enter positive weight value: ";
                }
            }
            default: {
                sessionService.setBotState(chatId, State.DEFAULT);
                yield "Command cancelled";
            }
        };
    }

    @Override
    public String getDescription() {
        return "Add product to daily ration";
    }
}
