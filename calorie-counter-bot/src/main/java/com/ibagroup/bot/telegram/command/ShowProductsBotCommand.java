package com.ibagroup.bot.telegram.command;

import com.ibagroup.bot.command.Command;
import com.ibagroup.common.domain.dto.ProductDto;
import com.ibagroup.common.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ShowProductsBotCommand implements BotCommand{

    private static final String GRAM = " g";
    private static final String KCAL = " kcal";
    private static final String RIGHT_BRACKET = ")";
    private static final String FORMAT = "|%1$-10s|%2$-20s|%3$-10s|%4$-10s|%5$-10s|%6$-10s|\n";
    private static final String[] HEADERS = {"Number", "Name", "Proteins", "Carbs", "Fats", "Calories"};

    private final ProductService productService;

    @Override
    public Command getCommand() {
        return Command.SHOW_PRODUCTS;
    }

    @Override
    public String execute(Update update) {
        List<ProductDto> products = productService.getProducts();

        StringBuilder productsInfo = new StringBuilder();
        productsInfo.append(String.format(FORMAT, HEADERS));
        for(int i = 0; i < products.size(); ++i){
            productsInfo.append(String.format(FORMAT, i + 1 + RIGHT_BRACKET,
                    products.get(i).getName(),
                    products.get(i).getProteins() + GRAM,
                    products.get(i).getCarbs() + GRAM,
                    products.get(i).getFats() + GRAM,
                    products.get(i).getCalories() + KCAL));
        }

        return productsInfo.toString();
    }

    @Override
    public String getDescription() {
        return "Show products registered in the system";
    }
}
