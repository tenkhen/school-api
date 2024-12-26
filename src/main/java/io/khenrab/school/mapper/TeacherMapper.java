package io.khenrab.school.mapper;

import io.khenrab.school.dto.TeacherDto;
import io.khenrab.school.entity.Teacher;

public class TeacherMapper {
    public static TeacherDto mapToTeacherDto(Teacher teacher) {
        if(teacher == null) return null;

        return new TeacherDto(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getEmail(),
                teacher.getSpecialization()
        );
    }

    public static Teacher mapToTeacher(TeacherDto teacherDto) {
        if(teacherDto == null) return null;

        return new Teacher(
                teacherDto.getId(),
                teacherDto.getFirstName(),
                teacherDto.getLastName(),
                teacherDto.getEmail(),
                teacherDto.getSpecialization()
        );
    }
}
