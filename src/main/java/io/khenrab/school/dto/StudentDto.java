package io.khenrab.school.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Student Schema")
//@Schema(description = "Data transfer object representing a student")
public class StudentDto {
    @Schema(description = "Unique ID of the student", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "First name of the student", example = "John")
    private String firstName;

    @Schema(description = "Last name of the student", example = "Doe")
    private String lastName;

    @Schema(description = "Email of the student", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Date of birth of the student", example = "2000-01-15")
    private LocalDate dateOfBirth;
}
