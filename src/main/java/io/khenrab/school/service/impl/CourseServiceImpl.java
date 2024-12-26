package io.khenrab.school.service.impl;

import io.khenrab.school.dto.CourseDto;
import io.khenrab.school.entity.Course;
import io.khenrab.school.entity.Teacher;
import io.khenrab.school.exception.ResourceNotFoundException;
import io.khenrab.school.mapper.CourseMapper;
import io.khenrab.school.repository.CourseRepository;
import io.khenrab.school.repository.TeacherRepository;
import io.khenrab.school.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(CourseMapper::mapToCourseDto)
                .toList();
    }

    @Override
    public CourseDto getCourseById(Long courseId) {
        return CourseMapper.mapToCourseDto(findCourse(courseId));
    }

    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        Teacher teacher = findTeacher(courseDto.getTeacherId());
        Course course = CourseMapper.mapToCourse(courseDto, teacher);

        return CourseMapper.mapToCourseDto(courseRepository.save(course));
    }

    @Override
    public CourseDto updateCourse(Long courseId, CourseDto courseDto) {
        Course course = findCourse(courseId);
        Teacher teacher = findTeacher(courseDto.getTeacherId());

        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setTeacher(teacher);

        return CourseMapper.mapToCourseDto(courseRepository.save(course));
    }

    @Override
    public void deleteCourse(Long courseId) {
        Course course = findCourse(courseId);
        courseRepository.delete(course);
    }

    private Course findCourse(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
    }

    private Teacher findTeacher(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + teacherId));
    }
}
