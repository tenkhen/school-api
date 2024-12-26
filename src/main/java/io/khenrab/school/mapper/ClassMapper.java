package io.khenrab.school.mapper;

import io.khenrab.school.dto.ClassDto;
import io.khenrab.school.entity.Class;
import io.khenrab.school.entity.Course;

public class ClassMapper {
    public static ClassDto mapToClassDto(Class cls) {
        if(cls == null) return null;

        return new ClassDto(
                cls.getId(),
                cls.getCourse().getId(),
                cls.getRoom(),
                cls.getSchedule()
        );
    }

    public static Class mapToClass(ClassDto classDto, Course course) {
        if(classDto == null || course == null) return null;

        return new Class(
                classDto.getId(),
                course,
                classDto.getRoom(),
                classDto.getSchedule()
        );
    }
}
