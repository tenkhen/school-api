package io.khenrab.school.controller;

import io.khenrab.school.dto.PageDto;
import io.khenrab.school.dto.StudentDto;
import io.khenrab.school.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students")
@Tag(name = "Student", description = "APIs for managing students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    @Operation(summary = "Get all students", description = "Retrieve all students with optional pagination.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved students", content = @Content(mediaType = "application/json"))
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/page")
    @Operation(summary = "Get paginated students", description = "Retrieve a paginated list of students with optional query parameters for pagination.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved paginated students", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<PageDto<StudentDto>> getAllStudents(
            @Parameter(description = "Page number (0-based index)", example = "4")
            @RequestParam(value = "page", defaultValue = "0") int page,

            @Parameter(description = "Number of items per page", example = "10")
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(studentService.getAllStudents(page, size));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get student by ID", description = "Retrieve a specific student by their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the student", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<StudentDto> getStudentById(
            @Parameter(description = "ID of the student to be retrieved", example = "1", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Search students by name", description = "Search for students using their name with pagination support.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved matching students", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input or parameters", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Page<StudentDto>> searchStudents(
            @Parameter(description = "Name of the student to search for (case-insensitive", example = "John", required = true)
            @RequestParam String name,

            @Parameter(description = "Page number (0-based index)", example = "0")
            @RequestParam(value = "page", defaultValue = "0") int page,

            @Parameter(description = "Number of items per page", example = "10")
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(studentService.getStudentsByName(name, page, size));
    }

    @PostMapping
    @Operation(summary = "Create a new student", description = "Add a new student to the database by providing student details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student successfully created", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid student details provided", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<StudentDto> createStudent(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Details of the student to be created",
                    required = true,
                    content = @Content(mediaType = "application/json")
            )
            @RequestBody StudentDto studentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(studentDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing student", description = "Update the details of a specific student using their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student successfully updated", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input or missing fields", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<StudentDto> updateStudent(
            @Parameter(description = "ID of the student to be updated", example = "1", required = true)
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated details of the student",
                    required = true,
                    content = @Content(mediaType = "application/json")
            )
            @RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.updateStudent(id, studentDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing student", description = "Delete an existing student using their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student successfully deleted", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Void> deleteStudent(
            @Parameter(description = "ID of the student to be deleted", example = "1", required = true)
            @PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
