package com.ibagroup.common.domain.mapper;

import com.ibagroup.common.dao.mongo.collection.Course;
import com.ibagroup.common.domain.dto.CourseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDto toDto(Course course);

}
