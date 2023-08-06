package com.ibagroup.bot.telegram.command;

import com.ibagroup.bot.command.Command;
import com.ibagroup.common.domain.dto.ProductRegistrationDto;
import com.ibagroup.common.mongo.collection.State;
import com.ibagroup.common.service.ProductService;
import com.ibagroup.common.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class RegisterProductBotCommand implements BotCommand{

    private final static String TEXT_PATTERN = "[a-zA-Z ]*";
    private final static String NUMBER_PATTERN = "[0-9]+\\.?[0-9]*";

    private ProductRegistrationDto productRegistrationDto = new ProductRegistrationDto();

    private final SessionService sessionService;
    private final ProductService productService;

    @Override
    public Command getCommand() {
        return Command.REGISTER_PRODUCT;
    }

    @Override
    public String execute(Update update) {
        Long chatId = update.getMessage().getChatId();
        if(!sessionService.isUserConfirmed(chatId)) {
            return "You need to authenticate to use this command";
        }

        State state = sessionService.getSession(chatId).getState();
        return switch (state){
            case DEFAULT: {
                sessionService.setBotState(chatId, State.ADDING_PRODUCT_ENTER_NAME);
                yield "Please enter product name: ";
            }
            case ADDING_PRODUCT_ENTER_NAME: {
                String name = update.getMessage().getText();
                if(Pattern.matches(TEXT_PATTERN, name)){
                    productRegistrationDto.setName(name);
                    sessionService.setBotState(chatId, State.ADDING_PRODUCT_ENTER_PROTEINS);
                    yield "Please enter protein content: ";
                } else {
                    yield "Please use letters for product name";
                }
            }
            case ADDING_PRODUCT_ENTER_PROTEINS: {
                String proteins = update.getMessage().getText();
                if(Pattern.matches(NUMBER_PATTERN, proteins) && Float.valueOf(proteins) >= 0) {
                    productRegistrationDto.setProteins(Float.valueOf(proteins));
                    sessionService.setBotState(chatId, State.ADDING_PRODUCT_ENTER_CARBS);
                    yield "Please enter carbs content: ";
                } else {
                    yield "Please enter not negative proteins value: ";
                }
            }
            case ADDING_PRODUCT_ENTER_CARBS: {
                String carbs = update.getMessage().getText();
                if(Pattern.matches(NUMBER_PATTERN, carbs) && Float.valueOf(carbs) >= 0) {
                    productRegistrationDto.setCarbs(Float.valueOf(carbs));
                    sessionService.setBotState(chatId, State.ADDING_PRODUCT_ENTER_FATS);
                    yield "Please enter fats content: ";
                } else {
                    yield "Please enter not negative carbs value: ";
                }
            }
            case ADDING_PRODUCT_ENTER_FATS: {
                String fats = update.getMessage().getText();
                if(Pattern.matches(NUMBER_PATTERN, fats) && Float.valueOf(fats) >= 0) {
                    productRegistrationDto.setFats(Float.valueOf(fats));
                    productService.createProduct(productRegistrationDto);
                    sessionService.setBotState(chatId, State.DEFAULT);
                    yield "Product successfully added";
                } else {
                    yield "Please enter not negative fats value: ";
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
        return "Registers product in the system";
    }

    @Override
    public List<State> getStates() {
        return List.of(State.ADDING_PRODUCT_ENTER_NAME,
                State.ADDING_PRODUCT_ENTER_PROTEINS,
                State.ADDING_PRODUCT_ENTER_CARBS,
                State.ADDING_PRODUCT_ENTER_FATS);
    }
}
