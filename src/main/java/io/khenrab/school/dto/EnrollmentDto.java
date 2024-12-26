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
@Schema(name = "Enrollment Schema")
public class EnrollmentDto {

    @Schema(description = "Unique ID of the enrollment", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Unique ID of the student to enroll", example = "1")
    private Long studentId;

    @Schema(description = "Unique ID of the course to enroll", example = "1")
    private Long courseId;

    @Schema(description = "Date of enrollment", example = "2024-12-30")
    private LocalDate enrollmentDate;
}
