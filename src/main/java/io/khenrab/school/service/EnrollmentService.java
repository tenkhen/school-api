package io.khenrab.school.service;

import io.khenrab.school.dto.EnrollmentDto;

import java.util.List;

public interface EnrollmentService {
    List<EnrollmentDto> getAllEnrollments();
    List<EnrollmentDto> getEnrollmentsByStudentId(Long studentId);
    List<EnrollmentDto> getEnrollmentsByCourseId(Long courseId);
    EnrollmentDto enrollStudentInCourse(EnrollmentDto enrollmentDto);
    void removeEnrollment(Long enrollmentId);
}
