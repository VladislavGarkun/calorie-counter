package com.ibagroup.common.service;

import com.ibagroup.common.domain.dto.CourseDto;
import com.ibagroup.common.domain.dto.CourseRegistrationDto;

import java.util.List;

public interface CourseService {

    void createCourse(CourseRegistrationDto courseRegistrationDto);

    List<CourseDto> getCoursesByChatId(Long chatId);

    boolean isCourseNew(String productId);

    void updateCourse(String productId, Float weight);

}
