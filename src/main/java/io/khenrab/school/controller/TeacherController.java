package io.khenrab.school.controller;

import io.khenrab.school.dto.TeacherDto;
import io.khenrab.school.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teachers")
@Tag(name = "Teacher", description = "APIs for managing teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    @Operation(summary = "Get all teachers", description = "Retrieve all teachers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved teachers", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get teacher by ID", description = "Retrieve a specific teacher by their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the teacher", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Teacher not found", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<TeacherDto> getTeacherById(
            @Parameter(description = "ID of the teacher to be retrieved", example = "1", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new teacher", description = "Add a new teacher to the database by providing teacher details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Teacher successfully created", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid teacher details provided", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<TeacherDto> createTeacher(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Details of the teacher to be created",
                    required = true,
                    content = @Content(mediaType = "application/json")
            )
            @RequestBody TeacherDto teacherDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(teacherDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing teacher", description = "Update the details of a specific teacher using their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher successfully updated", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input or missing fields", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Teacher not found", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<TeacherDto> updateTeacher(
            @Parameter(description = "ID of the teacher to be updated")
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated details of the teacher",
                    required = true,
                    content = @Content(mediaType = "application/json")
            )
            @RequestBody TeacherDto teacherDto) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, teacherDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete existing teacher", description = "Delete an existing teacher using their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Teacher successfully deleted", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Teacher not found", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Void> deleteTeacher(
            @Parameter(description = "ID of the teacher to be deleted", example = "1", required = true)
            @PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
