package dev.matheusferreira.courses_api.modules.courses.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.matheusferreira.courses_api.modules.courses.dtos.CreateCourseDTO;
import dev.matheusferreira.courses_api.modules.courses.dtos.ListCoursesDTO;
import dev.matheusferreira.courses_api.modules.courses.dtos.UpdateCourseDTO;
import dev.matheusferreira.courses_api.modules.courses.entities.CourseEntity;
import dev.matheusferreira.courses_api.modules.courses.useCases.CreateCourseUseCase;
import dev.matheusferreira.courses_api.modules.courses.useCases.ListCoursesUseCase;
import dev.matheusferreira.courses_api.modules.courses.useCases.UpdateCourseUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CoursesController {

  @Autowired
  private ListCoursesUseCase listCoursesUseCase;

  @Autowired
  private CreateCourseUseCase createCourseUseCase;

  @Autowired
  private UpdateCourseUseCase updateCourseUseCase;
  
  @GetMapping
  public List<CourseEntity> listCourses(@RequestBody @Valid ListCoursesDTO listCoursesDTO) {
    return listCoursesUseCase.execute(listCoursesDTO);
  }

  @PostMapping
  public CourseEntity createCourse(@RequestBody @Valid CreateCourseDTO createCourseDTO) {
    return createCourseUseCase.execute(createCourseDTO);
  }

  @PutMapping("/{courseId}")
  public CourseEntity updateCourse(@PathVariable("courseId") String courseId, @RequestBody @Valid UpdateCourseDTO updateCourseDTO) {
    updateCourseDTO.setId(UUID.fromString(courseId));
    return updateCourseUseCase.execute(updateCourseDTO);
  }

}
