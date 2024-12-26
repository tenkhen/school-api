package io.khenrab.school.controller;

import io.khenrab.school.dto.ClassDto;
import io.khenrab.school.service.ClassService;
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
@RequestMapping("/api/classes")
@Tag(name = "Class", description = "APIs for managing classes")
public class ClassController {
    private final ClassService classService;

    @GetMapping
    @Operation(summary = "Get all classes", description = "Retrieve all classes")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved classes", content = @Content(mediaType = "application/json"))
    public ResponseEntity<List<ClassDto>> getAllClasses() {
        return ResponseEntity.ok(classService.getAllClasses());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get class by ID", description = "Retrieve a specific class by their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the class", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Class not found", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<ClassDto> getClassById(
            @Parameter(description = "ID of the class to be retrieved", example = "1", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(classService.getClassById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new class", description = "Add a new class to the database by providing class details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Class successfully created", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid class details provided", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<ClassDto> createClass(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Details of the class to be created",
                    required = true,
                    content = @Content(mediaType = "application/json")
            )
            @RequestBody ClassDto classDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(classService.createClass(classDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing class", description = "Update the details of a specific class using their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Class successfully updated", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input or missing fields", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Class not found", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<ClassDto> updateClass(
            @Parameter(description = "ID of the class to be updated", example = "1", required = true)
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated details of the class",
                    required = true,
                    content = @Content(mediaType = "application/json")
            )
            @RequestBody ClassDto classDto) {
        return ResponseEntity.ok(classService.updateClass(id, classDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing class", description = "Delete an existing class using their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Class successfully deleted", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Class not found", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Void> deleteClass(
            @Parameter(description = "ID of the class to be deleted", example = "1", required = true)
            @PathVariable Long id) {
        classService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }
}
