package com.ibagroup.common.service.impl;

import com.ibagroup.common.dao.mongo.collection.Course;
import com.ibagroup.common.dao.mongo.repository.CourseRepository;
import com.ibagroup.common.domain.dto.CourseDto;
import com.ibagroup.common.domain.dto.CourseRegistrationDto;
import com.ibagroup.common.domain.mapper.CourseMapper;
import com.ibagroup.common.domain.mapper.CourseRegistrationMapper;
import com.ibagroup.common.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;
    private final CourseRegistrationMapper courseRegistrationMapper;

    public void createCourse(CourseRegistrationDto courseRegistrationDto) {
        Course course = courseRegistrationMapper.toEntity(courseRegistrationDto);
        courseRepository.insert(course);
    }

    public List<CourseDto> getCoursesByChatId(Long chatId) {
        List<Course> courses = courseRepository.findAllBySessionId(chatId);
        return courses
                .stream()
                .filter(course -> course.getCourseDateTime().toLocalDate().isEqual(LocalDate.now()))
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

    public boolean isCourseNew(String productId){
        return courseRepository.findAllByProductId(productId)
                .stream()
                .noneMatch(course -> course.getCourseDateTime().toLocalDate().isEqual(LocalDate.now()));
    }

    private Course getCourseByProductId(String productId){
        return courseRepository.findAllByProductId(productId)
                .stream()
                .filter(course -> course.getCourseDateTime().toLocalDate().isEqual(LocalDate.now()))
                .collect(Collectors.toList())
                .get(0);
    }

    public void updateCourse(String productId, Float weight){
        Course course = getCourseByProductId(productId);
        course.setWeight(course.getWeight() + weight);
        courseRepository.save(course);
    }
}
