package dev.matheusferreira.courses_api.modules.courses.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.matheusferreira.courses_api.modules.courses.dtos.ListCoursesDTO;
import dev.matheusferreira.courses_api.modules.courses.entities.CourseEntity;
import dev.matheusferreira.courses_api.modules.courses.useCases.ListCoursesUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CoursesController {

  @Autowired
  private ListCoursesUseCase listCoursesUseCase;
  
  @GetMapping
  public List<CourseEntity> listCourses(@RequestBody @Valid ListCoursesDTO listCoursesDTO) {
    return listCoursesUseCase.execute(listCoursesDTO);
  }

}
