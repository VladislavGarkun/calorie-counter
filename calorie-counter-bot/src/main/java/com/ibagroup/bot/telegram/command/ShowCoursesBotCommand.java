package com.ibagroup.bot.telegram.command;

import com.ibagroup.bot.command.Command;
import com.ibagroup.common.domain.dto.CourseDto;
import com.ibagroup.common.domain.dto.EatenCourseDto;
import com.ibagroup.common.domain.dto.ProductDto;
import com.ibagroup.common.domain.mapper.EatenCourseMapper;
import com.ibagroup.common.service.CourseService;
import com.ibagroup.common.service.ProductService;
import com.ibagroup.common.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ShowCoursesBotCommand implements BotCommand {

    private static final String GRAM = " g";
    private static final String KCAL = " kcal";
    private static final String RIGHT_BRACKET = ")";
    private static final String FORMAT = "|%1$-10s|%2$-30s|%3$-20s|%4$-10s|%5$-10s|%6$-10s|%7$-10s|%8$-20s|\n";
    private static final String[] HEADERS = {"Number", "Name", "Weight", "Proteins", "Carbs", "Fats", "Calories", "Time"};

    private final CourseService courseService;
    private final SessionService sessionService;
    private final ProductService productService;
    private final EatenCourseMapper eatenCourseMapper;

    @Override
    public Command getCommand() {
        return Command.SHOW_COURSES;
    }

    @Override
    public String execute(Update update) {
        Long chatId = update.getMessage().getChatId();
        if(!sessionService.isUserConfirmed(chatId)) {
            return "You need to authenticate to use this command";
        }

        List<CourseDto> courseDtoList = courseService.getCoursesByChatId(chatId);
        List<String> productIds = courseDtoList.stream().map(CourseDto::getProductId).toList();
        Map<String, ProductDto> productDtoMap = productService.getProductsByIds(productIds);

        List<EatenCourseDto> eatenCourseDtoList = eatenCourseMapper.toDto(courseDtoList, productDtoMap);

        StringBuilder eatenCoursesInfo = new StringBuilder();
        eatenCoursesInfo.append(String.format(FORMAT, HEADERS));
        for(int i = 0; i < eatenCourseDtoList.size(); ++i){
            eatenCoursesInfo.append(String.format(FORMAT, i + 1 + RIGHT_BRACKET,
                    eatenCourseDtoList.get(i).getName(),
                    eatenCourseDtoList.get(i).getWeight() + GRAM,
                    eatenCourseDtoList.get(i).getProteins() + GRAM,
                    eatenCourseDtoList.get(i).getCarbs() + GRAM,
                    eatenCourseDtoList.get(i).getFats() + GRAM,
                    eatenCourseDtoList.get(i).getCalories() + KCAL,
                    mapToString(eatenCourseDtoList.get(i).getCourseDateTime().toLocalTime())));
        }
        float totalWeight = summarizeTotal(eatenCourseDtoList, EatenCourseDto::getWeight);
        float totalProteins = summarizeTotal(eatenCourseDtoList, EatenCourseDto::getProteins);
        float totalCarbs = summarizeTotal(eatenCourseDtoList, EatenCourseDto::getCarbs);
        float totalFats = summarizeTotal(eatenCourseDtoList, EatenCourseDto::getFats);
        float totalCalories = summarizeTotal(eatenCourseDtoList, EatenCourseDto::getCalories);

        eatenCoursesInfo.append(String.format(FORMAT, 0 + RIGHT_BRACKET,
                "Summary",
                totalWeight + GRAM,
                totalProteins + GRAM,
                totalCarbs + GRAM,
                totalFats + GRAM,
                totalCalories + KCAL,
                mapToString(LocalTime.now())));

        return eatenCoursesInfo.toString();
    }

    @Override
    public String getDescription() {
        return "Shows courses which you have eaten today";
    }

    private float summarizeTotal(List<EatenCourseDto> eatenCourseDtoList, Function<EatenCourseDto, Float> function){
        return (float) eatenCourseDtoList.stream().map(function).mapToDouble(x -> (double)x).sum();
    }

    private String mapToString(LocalTime localTime){
        return localTime.getHour() + ":" + localTime.getMinute() + ":" + localTime.getSecond();
    }
}
