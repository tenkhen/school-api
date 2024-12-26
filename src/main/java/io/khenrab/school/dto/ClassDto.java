package io.khenrab.school.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Class Schema")
public class ClassDto {

    @Schema(description = "Unique ID of the class", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Unique ID of the course", example = "1")
    private Long courseId;

    @Schema(description = "Name of the room", example = "Auditorium A")
    private String room;

    @Schema(description = "Scheduled date", example = "2024-12-24T19:57:21.424Z")
    private LocalDateTime schedule;
}
