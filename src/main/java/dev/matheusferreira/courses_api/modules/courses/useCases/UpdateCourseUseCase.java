package dev.matheusferreira.courses_api.modules.courses.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.matheusferreira.courses_api.modules.courses.dtos.UpdateCourseDTO;
import dev.matheusferreira.courses_api.modules.courses.entities.CourseEntity;
import dev.matheusferreira.courses_api.modules.courses.exceptions.CourseNotFoundException;
import dev.matheusferreira.courses_api.modules.courses.repositories.CoursesRepository;

@Service
public class UpdateCourseUseCase {
  
  @Autowired
  private CoursesRepository coursesRepository;

  public CourseEntity execute(UpdateCourseDTO updateCourseDTO) {
    CourseEntity courseEntity = this.coursesRepository.findById(updateCourseDTO.getId())
      .orElseThrow(() -> {
        throw new CourseNotFoundException();
      });

    if(updateCourseDTO.getName() != null) {
      courseEntity.setName(updateCourseDTO.getName());
    }

    if(updateCourseDTO.getCategory() != null) {
      courseEntity.setCategory(updateCourseDTO.getCategory());
    }

    return this.coursesRepository.save(courseEntity);
  }

}
