package com.ibagroup.common.domain.mapper;

import com.ibagroup.common.domain.dto.CourseRegistrationDto;
import com.ibagroup.common.dao.mongo.collection.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseRegistrationMapper {

    Course toEntity(CourseRegistrationDto courseRegistrationDto);

}
