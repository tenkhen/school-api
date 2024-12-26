package io.khenrab.school.controller;

import io.khenrab.school.dto.CourseDto;
import io.khenrab.school.service.CourseService;
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
@RequestMapping("/api/courses")
@Tag(name = "Course", description = "APIs for managing courses")
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    @Operation(summary = "Get all courses", description = "Retrieve all courses")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved courses", content = @Content(mediaType = "application/json"))
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get course by ID", description = "Retrieve a specific course by their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the course", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Course not found", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<CourseDto> getCourseById(
            @Parameter(description = "ID of the course to be retrieve", example = "1", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new course", description = "Add a new course to the database by providing course details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course successfully created", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid course details provided", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<CourseDto> createCourse(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Details of the course to be created",
                    required = true,
                    content = @Content(mediaType = "application/json")
            )
            @RequestBody CourseDto courseDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(courseDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing course", description = "Update the details of a specific course using their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course successfully updated", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input or missing fields", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Course not found", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<CourseDto> updateCourse(
            @Parameter(description = "ID of the course to be updated", example = "1", required = true)
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated details of the course",
                    required = true,
                    content = @Content(mediaType = "application/json")
            )
            @RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing course", description = "Delete an existing course using their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Course successfully deleted", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Course not found", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Void> deleteCourse(
            @Parameter(description = "ID of the course to be deleted", example = "1", required = true)
            @PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
