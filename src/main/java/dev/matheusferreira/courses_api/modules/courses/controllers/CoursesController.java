package dev.matheusferreira.courses_api.modules.courses.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.matheusferreira.courses_api.exceptions.ExceptionDTO;
import dev.matheusferreira.courses_api.modules.courses.dtos.CreateCourseDTO;
import dev.matheusferreira.courses_api.modules.courses.dtos.ListCoursesDTO;
import dev.matheusferreira.courses_api.modules.courses.dtos.UpdateCourseDTO;
import dev.matheusferreira.courses_api.modules.courses.entities.CourseEntity;
import dev.matheusferreira.courses_api.modules.courses.useCases.CreateCourseUseCase;
import dev.matheusferreira.courses_api.modules.courses.useCases.DeleteCourseUseCase;
import dev.matheusferreira.courses_api.modules.courses.useCases.ListCoursesUseCase;
import dev.matheusferreira.courses_api.modules.courses.useCases.ToggleCourseActiveUseCase;
import dev.matheusferreira.courses_api.modules.courses.useCases.UpdateCourseUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Cursos", description = "CRUD de cursos")
public class CoursesController {

  @Autowired
  private ListCoursesUseCase listCoursesUseCase;

  @Autowired
  private CreateCourseUseCase createCourseUseCase;

  @Autowired
  private UpdateCourseUseCase updateCourseUseCase;

  @Autowired
  private DeleteCourseUseCase deleteCourseUseCase;

  @Autowired
  private ToggleCourseActiveUseCase toggleCourseActiveUseCase;
  
  @GetMapping
  @Operation(summary = "Listagem de cursos", description = "Endpoint responsável por fazer a listagem de todos os cursos cadastrados com filtro de nome e categoria do curso.")
  @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CourseEntity.class)))
  @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionDTO.class)))
  @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ExceptionDTO.class), examples = @ExampleObject(value = "{\"timestamp\":\"2024-06-17T15:35:28.363339\",\"status\":\"INTERNAL_SERVER_ERROR\",\"message\":\"Internal Server Error\",\"details\":null}")))
  public List<CourseEntity> listCourses(@Valid ListCoursesDTO listCoursesDTO) {
    return listCoursesUseCase.execute(listCoursesDTO);
  }

  @PostMapping
  @Operation(summary = "Criação de cursos", description = "Endpoint responsável por fazer a criação de um curso.")
  @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CourseEntity.class)))
  @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ExceptionDTO.class)))
  @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = ExceptionDTO.class), examples = @ExampleObject(value = "{\"timestamp\":\"2024-06-17T15:35:28.363339\",\"status\":\"CONFLICT\",\"message\":\"Um curso com o mesmo nome e categoria já está cadastrado\",\"details\":null}")))
  @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ExceptionDTO.class), examples = @ExampleObject(value = "{\"timestamp\":\"2024-06-17T15:35:28.363339\",\"status\":\"INTERNAL_SERVER_ERROR\",\"message\":\"Internal Server Error\",\"details\":null}")))
  public CourseEntity createCourse(@RequestBody @Valid CreateCourseDTO createCourseDTO) {
    return createCourseUseCase.execute(createCourseDTO);
  }

  @PutMapping("/{courseId}")
  public CourseEntity updateCourse(@PathVariable("courseId") String courseId, @RequestBody @Valid UpdateCourseDTO updateCourseDTO) {
    updateCourseDTO.setId(UUID.fromString(courseId));
    return updateCourseUseCase.execute(updateCourseDTO);
  }

  @DeleteMapping("/{courseId}")
  public ResponseEntity<Object> deleteCourse(@PathVariable("courseId") String courseId) {
    deleteCourseUseCase.execute(UUID.fromString(courseId));

    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{courseId}/active")
  public CourseEntity toggleCourseActive(@PathVariable("courseId") String courseId) {
    return toggleCourseActiveUseCase.execute(UUID.fromString(courseId));
  }

}
