package dev.matheusferreira.courses_api.modules.courses.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.matheusferreira.courses_api.modules.courses.dtos.ListCoursesDTO;
import dev.matheusferreira.courses_api.modules.courses.entities.CourseEntity;
import dev.matheusferreira.courses_api.modules.courses.repositories.CoursesRepository;

@Service
public class ListCoursesUseCase {
  
  @Autowired
  private CoursesRepository coursesRepository;

  public List<CourseEntity> execute(ListCoursesDTO listCoursesDTO) {
    return this.coursesRepository.findByCategoryAndNameContainingIgnoreCase(
      listCoursesDTO.getCategory(),
      listCoursesDTO.getName()
    );
  }

}
