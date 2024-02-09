package com.ibagroup.common.service;

import com.ibagroup.common.domain.dto.CourseDto;
import com.ibagroup.common.domain.dto.CourseRegistrationDto;
import com.ibagroup.common.domain.mapper.CourseMapper;
import com.ibagroup.common.domain.mapper.CourseRegistrationMapper;
import com.ibagroup.common.dao.mongo.collection.Course;
import com.ibagroup.common.dao.mongo.repository.CourseRepository;
import com.ibagroup.common.service.impl.CourseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceUnitTest {

    private static final LocalDateTime dateTime = LocalDateTime.now();

    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private CourseMapper courseMapper;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private CourseRegistrationMapper courseRegistrationMapper;

    @Test
    public void shouldSaveCourse_whenCreateCourse_givenCourseRegistrationDto(){
        // given
        CourseRegistrationDto courseRegistrationDto = new CourseRegistrationDto("productId", 1L, 100f, LocalDateTime.MAX);
        Course course = prepareCourse("productId", LocalDateTime.MAX);

        when(courseRegistrationMapper.toEntity(any())).thenReturn(course);
        when(courseRepository.insert(any(Course.class))).thenReturn(course);

        // when
        courseService.createCourse(courseRegistrationDto);

        // then
        verify(courseRegistrationMapper).toEntity(courseRegistrationDto);
        verify(courseRepository).insert(course);
    }

    @Test
    public void shouldReturnCourseDtoList_whenGetCoursesByChatId_givenChatId(){
        // given
        List<Course> courses = prepareCourses();
        CourseDto courseDto = prepareCourseDto();
        List<CourseDto> expected = List.of(courseDto);

        when(courseRepository.findAllBySessionId(any())).thenReturn(courses);
        when(courseMapper.toDto(any())).thenReturn(courseDto);

        // when
        List<CourseDto> actual = courseService.getCoursesByChatId(1L);

        // then
        assertThat(actual).isEqualTo(expected);
        verify(courseRepository).findAllBySessionId(1L);
        verify(courseMapper).toDto(courses.get(0));
    }

    private List<Course> prepareCourses(){
        return List.of(prepareCourse("productId1", dateTime), prepareCourse("productId2", dateTime.minusDays(1)));
    }

    private Course prepareCourse(String id, LocalDateTime courseDateTime){
        Course course = new Course();
        course.setProductId(id);
        course.setSessionId(1L);
        course.setWeight(100f);
        course.setCourseDateTime(courseDateTime);
        return course;
    }

    private CourseDto prepareCourseDto(){
        CourseDto courseDto = new CourseDto();
        courseDto.setProductId("productId1");
        courseDto.setSessionId(1L);
        courseDto.setWeight(100f);
        courseDto.setCourseDateTime(dateTime);
        return courseDto;
    }

}
