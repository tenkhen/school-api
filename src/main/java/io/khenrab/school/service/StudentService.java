package io.khenrab.school.service;

import io.khenrab.school.dto.PageDto;
import io.khenrab.school.dto.StudentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();
    PageDto<StudentDto> getAllStudents(int page, int size);
    StudentDto getStudentById(Long studentId);
    Page<StudentDto> getStudentsByName(String firstName, int page, int size);
    StudentDto createStudent(StudentDto studentDto);
    StudentDto updateStudent(Long studentId, StudentDto studentDto);
    void deleteStudent(Long studentId);
}
