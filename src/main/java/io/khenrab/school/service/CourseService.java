package io.khenrab.school.service;

import io.khenrab.school.dto.CourseDto;

import java.util.List;

public interface CourseService {
    List<CourseDto> getAllCourses();
    CourseDto getCourseById(Long courseId);
    CourseDto createCourse(CourseDto courseDto);
    CourseDto updateCourse(Long courseId, CourseDto courseDto);
    void deleteCourse(Long courseId);
}
