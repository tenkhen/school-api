package io.khenrab.school.mapper;

import io.khenrab.school.dto.StudentDto;
import io.khenrab.school.entity.Student;

public class StudentMapper {
    public static StudentDto mapToStudentDto(Student student) {
        if(student == null) return null;

        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getDateOfBirth()
        );
    }

    public static Student mapToStudent(StudentDto studentDto) {
        if(studentDto == null)  return null;

        return new Student(
                studentDto.getId(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getEmail(),
                studentDto.getDateOfBirth()
        );
    }
}
