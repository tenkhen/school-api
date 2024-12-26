package io.khenrab.school.service;

import io.khenrab.school.dto.TeacherDto;

import java.util.List;

public interface TeacherService {
    List<TeacherDto> getAllTeachers();
    TeacherDto getTeacherById(Long teacherId);
    TeacherDto createTeacher(TeacherDto teacherDto);
    TeacherDto updateTeacher(Long teacherId, TeacherDto teacherDto);
    void deleteTeacher(Long teacherId);
}
