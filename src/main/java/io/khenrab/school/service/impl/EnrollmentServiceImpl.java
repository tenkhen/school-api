package io.khenrab.school.service.impl;

import io.khenrab.school.dto.EnrollmentDto;
import io.khenrab.school.entity.Course;
import io.khenrab.school.entity.Enrollment;
import io.khenrab.school.entity.Student;
import io.khenrab.school.exception.ResourceNotFoundException;
import io.khenrab.school.mapper.EnrollmentMapper;
import io.khenrab.school.repository.CourseRepository;
import io.khenrab.school.repository.EnrollmentRepository;
import io.khenrab.school.repository.StudentRepository;
import io.khenrab.school.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<EnrollmentDto> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(EnrollmentMapper::mapToEnrollmentDto)
                .toList();
    }

    @Override
    public List<EnrollmentDto> getEnrollmentsByStudentId(Long studentId) {
        return enrollmentRepository.findAll().stream()
                .filter(enrollment -> enrollment.getStudent().getId().equals(studentId))
                .map(EnrollmentMapper::mapToEnrollmentDto)
                .toList();
    }

    @Override
    public List<EnrollmentDto> getEnrollmentsByCourseId(Long courseId) {
        return enrollmentRepository.findAll().stream()
                .filter(enrollment -> enrollment.getCourse().getId().equals(courseId))
                .map(EnrollmentMapper::mapToEnrollmentDto)
                .toList();
    }

    @Override
    public EnrollmentDto enrollStudentInCourse(EnrollmentDto enrollmentDto) {
        Student student = findStudent(enrollmentDto.getStudentId());
        Course course = findCourse(enrollmentDto.getCourseId());
        Enrollment enrollment = EnrollmentMapper.mapToEnrollment(enrollmentDto, student, course);

        return EnrollmentMapper.mapToEnrollmentDto(enrollmentRepository.save(enrollment));
    }

    @Override
    public void removeEnrollment(Long enrollmentId) {
        Enrollment enrollment = findEnrollment(enrollmentId);
        enrollmentRepository.delete(enrollment);
    }

    private Enrollment findEnrollment(Long enrollmentId) {
        return enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId));
    }
    private Student findStudent(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
    }

    private Course findCourse(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
    }
}
