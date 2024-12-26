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
@Schema(name = "Teacher Schema")
public class TeacherDto {
    @Schema(description = "Unique ID of the teacher", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "First name of the teacher", example = "John")
    private String firstName;

    @Schema(description = "Last name of the teacher", example = "Doe")
    private String lastName;

    @Schema(description = "Email of the teacher", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Specialization of the teacher", example = "Master of Computer Science")
    private String specialization;
}
