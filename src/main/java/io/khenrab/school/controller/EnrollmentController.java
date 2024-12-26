package io.khenrab.school.controller;

import io.khenrab.school.dto.EnrollmentDto;
import io.khenrab.school.service.EnrollmentService;
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
@RequestMapping("/api/enrollments")
@Tag(name = "Enrollment", description = "APIs for managing enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @GetMapping
    @Operation(summary = "Get all enrollments", description = "Retrieve all enrollments")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved enrollments", content = @Content(mediaType = "application/json"))
    public ResponseEntity<List<EnrollmentDto>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get enrollments by student ID", description = "Retrieve all enrollments by unique student ID.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved enrollments using student ID", content = @Content(mediaType = "application/json"))
    public ResponseEntity<List<EnrollmentDto>> getEnrollmentsByStudentId(
            @Parameter(description = "ID of the enrolled student", example = "1", required = true)
            @PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudentId(studentId));
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Get enrollments by course ID", description = "Retrieve all enrollments by unique course ID.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved enrollments using course ID", content = @Content(mediaType = "application/json"))
    public ResponseEntity<List<EnrollmentDto>> getEnrollmentsByCourseId(
            @Parameter(description = "ID of the enrolled course", example = "1", required = true)
            @PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByCourseId(courseId));
    }

    @PostMapping
    @Operation(summary = "Enroll a student in a course", description = "Enroll a specific student in a course by providing their IDs.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student successfully enrolled in the class", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input or enrollment already exists", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Student or Course not found", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<EnrollmentDto> enrollStudentInCourse(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Enrollment details including student ID and course ID",
                    required = true,
                    content = @Content(mediaType = "application/json")
            )
            @RequestBody EnrollmentDto enrollmentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.enrollStudentInCourse(enrollmentDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing enrollment", description = "Delete an existing enrollment using their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Enrollment successfully deleted", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Enrollment not found", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Void> removeEnrollment(
            @Parameter(description = "ID of the enrollment to be deleted", example = "1", required = true)
            @PathVariable Long id) {
        enrollmentService.removeEnrollment(id);
        return ResponseEntity.noContent().build();
    }
}
