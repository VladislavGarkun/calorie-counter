package com.ibagroup.bot.telegram.command;

import com.ibagroup.bot.command.Command;
import com.ibagroup.common.domain.dto.EatenMealDto;
import com.ibagroup.common.domain.dto.MealDto;
import com.ibagroup.common.domain.dto.ProductDto;
import com.ibagroup.common.domain.mapper.EatenMealMapper;
import com.ibagroup.common.service.MealService;
import com.ibagroup.common.service.ProductService;
import com.ibagroup.common.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ShowMealsBotCommand implements BotCommand {

    private static final String GRAM = " g";
    private static final String KCAL = " kcal";
    private static final String RIGHT_BRACKET = ")";
    private static final String FORMAT = "|%1$-10s|%2$-30s|%3$-20s|%4$-10s|%5$-10s|%6$-10s|%7$-10s|%8$-20s|\n";
    private static final String[] HEADERS = {"Number", "Name", "Weight", "Proteins", "Carbs", "Fats", "Calories", "Time"};

    private final EatenMealMapper eatenMealMapper;

    private final SessionService sessionService;

    private final ProductService productService;

    private final MealService mealService;

    @Override
    public Command getCommand() {
        return Command.SHOW_MEALS;
    }

    @Override
    public String execute(Update update) {
        Long chatId = update.getMessage().getChatId();
        if(!sessionService.isUserConfirmed(chatId)) {
            return "You need to authenticate to use this command";
        }

        List<MealDto> mealDtoList = mealService.getProductIdsByChatId(chatId);
        List<String> productIds = mealDtoList.stream().map(MealDto::getProductId).toList();
        Map<String, ProductDto> productDtoMap = productService.getProductsByIds(productIds);

        List<EatenMealDto> eatenMealDtoList = eatenMealMapper.toDto(mealDtoList, productDtoMap);

        StringBuilder eatenMealsInfo = new StringBuilder();
        eatenMealsInfo.append(String.format(FORMAT, HEADERS));
        for(int i = 0; i < eatenMealDtoList.size(); ++i){
            eatenMealsInfo.append(String.format(FORMAT, i + 1 + RIGHT_BRACKET,
                    eatenMealDtoList.get(i).getName(),
                    eatenMealDtoList.get(i).getWeight() + GRAM,
                    eatenMealDtoList.get(i).getProteins() + GRAM,
                    eatenMealDtoList.get(i).getCarbs() + GRAM,
                    eatenMealDtoList.get(i).getFats() + GRAM,
                    eatenMealDtoList.get(i).getCalories() + KCAL,
                    eatenMealDtoList.get(i).getMealDateTime()));
        }

        return eatenMealsInfo.toString();
    }

    @Override
    public String getDescription() {
        return "Shows meals which you have eaten today";
    }
}
