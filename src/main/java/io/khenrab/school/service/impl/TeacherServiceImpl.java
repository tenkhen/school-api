package io.khenrab.school.service.impl;

import io.khenrab.school.dto.TeacherDto;
import io.khenrab.school.entity.Teacher;
import io.khenrab.school.exception.ResourceNotFoundException;
import io.khenrab.school.mapper.TeacherMapper;
import io.khenrab.school.repository.TeacherRepository;
import io.khenrab.school.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public List<TeacherDto> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(TeacherMapper::mapToTeacherDto)
                .toList();
    }

    @Override
    public TeacherDto getTeacherById(Long teacherId) {
        return TeacherMapper.mapToTeacherDto(findTeacher(teacherId));
    }

    @Override
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        Teacher teacher = TeacherMapper.mapToTeacher(teacherDto);
        return TeacherMapper.mapToTeacherDto(teacherRepository.save(teacher));
    }

    @Override
    public TeacherDto updateTeacher(Long teacherId, TeacherDto teacherDto) {
        Teacher teacher = findTeacher(teacherId);
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setEmail(teacherDto.getEmail());
        teacher.setSpecialization(teacherDto.getSpecialization());

        return TeacherMapper.mapToTeacherDto(teacherRepository.save(teacher));
    }

    @Override
    public void deleteTeacher(Long teacherId) {
        Teacher teacher = findTeacher(teacherId);
        teacherRepository.delete(teacher);
    }

    private Teacher findTeacher(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + teacherId));
    }
}
