package com.ibagroup.common.domain.mapper.impl;

import com.ibagroup.common.domain.dto.CourseDto;
import com.ibagroup.common.domain.dto.EatenCourseDto;
import com.ibagroup.common.domain.dto.ProductDto;
import com.ibagroup.common.domain.mapper.EatenCourseMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class EatenCourseMapperImpl implements EatenCourseMapper {

    @Override
    public List<EatenCourseDto> toDto(List<CourseDto> courseDtoList, Map<String, ProductDto> productDtoMap) {
        List<EatenCourseDto> eatenCourseDtoList = new ArrayList<>();

        for(CourseDto courseDto : courseDtoList){
            EatenCourseDto eatenCourseDto = new EatenCourseDto();
            ProductDto productDto = productDtoMap.get(courseDto.getProductId());

            eatenCourseDto.setName(productDto.getName());
            eatenCourseDto.setWeight(courseDto.getWeight());
            eatenCourseDto.setProteins(productDto.getProteins() * courseDto.getWeight() / 100);
            eatenCourseDto.setCarbs(productDto.getCarbs() * courseDto.getWeight() / 100);
            eatenCourseDto.setFats(productDto.getFats() * courseDto.getWeight() / 100);
            eatenCourseDto.setCalories(productDto.getCalories() * courseDto.getWeight() / 100);
            eatenCourseDto.setCourseDateTime(courseDto.getCourseDateTime());

            eatenCourseDtoList.add(eatenCourseDto);
        }

        return eatenCourseDtoList;
    }

}
