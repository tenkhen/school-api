package io.khenrab.school.mapper;

import io.khenrab.school.dto.CourseDto;
import io.khenrab.school.entity.Course;
import io.khenrab.school.entity.Teacher;

public class CourseMapper {
    public static CourseDto mapToCourseDto(Course course) {
        if(course == null) return null;

        return new CourseDto(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getTeacher().getId()
        );
    }

    public static Course mapToCourse(CourseDto courseDto, Teacher teacher) {
        if(courseDto == null || teacher == null) return null;

        return new Course(
                courseDto.getId(),
                courseDto.getName(),
                courseDto.getDescription(),
                teacher
        );
    }
}
