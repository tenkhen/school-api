package io.khenrab.school.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Course Schema")
public class CourseDto {

    @Schema(description = "Unique ID of the course", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Name of the course", example = "Bachelor of Engineering")
    private String name;

    @Schema(description = "Description of the course", example = "An academic degree that focuses on engineering techniques and applications")
    private String description;

    @Schema(description = "Unique ID of the teacher", example = "1")
    private Long teacherId;
}
