package io.khenrab.school.service.impl;

import io.khenrab.school.dto.PageDto;
import io.khenrab.school.dto.StudentDto;
import io.khenrab.school.entity.Student;
import io.khenrab.school.exception.ResourceNotFoundException;
import io.khenrab.school.mapper.StudentMapper;
import io.khenrab.school.repository.StudentRepository;
import io.khenrab.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::mapToStudentDto)
                .toList();
    }

    @Override
    public PageDto<StudentDto> getAllStudents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<StudentDto> studentsPage = studentRepository.findAll(pageable)
                .map(StudentMapper::mapToStudentDto);

        PageDto<StudentDto> response = new PageDto<>(
                studentsPage.getContent(),
                studentsPage.getNumber(),
                studentsPage.getSize(),
                studentsPage.getTotalElements(),
                studentsPage.getTotalPages(),
                studentsPage.isLast()
        );

        return response;
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        return StudentMapper.mapToStudentDto(findStudent(studentId));
    }

    @Override
    public Page<StudentDto> getStudentsByName(String firstName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findByFirstNameContainingIgnoreCase(firstName, pageable)
                .map(StudentMapper::mapToStudentDto);
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto);

        return StudentMapper.mapToStudentDto(studentRepository.save(student));
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto studentDto) {
        Student student = findStudent(studentId);
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setDateOfBirth(studentDto.getDateOfBirth());

        return StudentMapper.mapToStudentDto(studentRepository.save(student));
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = findStudent(studentId);
        studentRepository.delete(student);
    }

    private Student findStudent(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
    }
}
