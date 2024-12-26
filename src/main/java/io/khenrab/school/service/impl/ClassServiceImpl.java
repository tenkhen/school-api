package io.khenrab.school.service.impl;

import io.khenrab.school.dto.ClassDto;
import io.khenrab.school.entity.Class;
import io.khenrab.school.entity.Course;
import io.khenrab.school.exception.ResourceNotFoundException;
import io.khenrab.school.mapper.ClassMapper;
import io.khenrab.school.repository.ClassRepository;
import io.khenrab.school.repository.CourseRepository;
import io.khenrab.school.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<ClassDto> getAllClasses() {
        return classRepository.findAll().stream()
                .map(ClassMapper::mapToClassDto)
                .toList();
    }

    @Override
    public ClassDto getClassById(Long classId) {
        return ClassMapper.mapToClassDto(findClass(classId));
    }

    @Override
    public ClassDto createClass(ClassDto classDto) {
        Course course = findCourse(classDto.getCourseId());
        Class cls = ClassMapper.mapToClass(classDto, course);

        return ClassMapper.mapToClassDto(classRepository.save(cls));
    }

    @Override
    public ClassDto updateClass(Long classId, ClassDto classDto) {
        Class cls = findClass(classId);
        Course course = findCourse(classDto.getCourseId());

        cls.setCourse(course);
        cls.setRoom(classDto.getRoom());
        cls.setSchedule(classDto.getSchedule());

        return ClassMapper.mapToClassDto(classRepository.save(cls));
    }

    @Override
    public void deleteClass(Long classId) {
        Class cls = findClass(classId);
        classRepository.delete(cls);
    }

    private Class findClass(Long classId) {
        return classRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + classId));
    }

    private Course findCourse(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
    }
}
