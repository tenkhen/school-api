package io.khenrab.school.mapper;

import io.khenrab.school.dto.EnrollmentDto;
import io.khenrab.school.entity.Course;
import io.khenrab.school.entity.Enrollment;
import io.khenrab.school.entity.Student;

public class EnrollmentMapper {
    public static EnrollmentDto mapToEnrollmentDto(Enrollment enrollment) {
        if(enrollment == null) return null;

        return new EnrollmentDto(
                enrollment.getId(),
                enrollment.getStudent().getId(),
                enrollment.getCourse().getId(),
                enrollment.getEnrollmentDate()
        );
    }

    public static Enrollment mapToEnrollment(EnrollmentDto enrollmentDto, Student student, Course course) {
        if(enrollmentDto == null || student == null || course == null) return null;

        return new Enrollment(
                enrollmentDto.getId(),
                student,
                course,
                enrollmentDto.getEnrollmentDate()
        );
    }
}
