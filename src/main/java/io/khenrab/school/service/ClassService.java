package io.khenrab.school.service;

import io.khenrab.school.dto.ClassDto;

import java.util.List;

public interface ClassService {
    List<ClassDto> getAllClasses();
    ClassDto getClassById(Long classId);
    ClassDto createClass(ClassDto classDto);
    ClassDto updateClass(Long classId, ClassDto classDto);
    void deleteClass(Long classId);
}
