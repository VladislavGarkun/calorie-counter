package com.ibagroup.common.domain.mapper;

import com.ibagroup.common.domain.dto.EatenCourseDto;
import com.ibagroup.common.domain.dto.CourseDto;
import com.ibagroup.common.domain.dto.ProductDto;

import java.util.List;
import java.util.Map;

public interface EatenCourseMapper {

    List<EatenCourseDto> toDto(List<CourseDto> courseDtoList, Map<String, ProductDto> productDtoMap);

}
