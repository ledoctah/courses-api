package dev.matheusferreira.courses_api.modules.courses.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.matheusferreira.courses_api.modules.courses.entities.CourseEntity;
import dev.matheusferreira.courses_api.modules.courses.exceptions.CourseNotFoundException;
import dev.matheusferreira.courses_api.modules.courses.repositories.CoursesRepository;

@Service
public class ToggleCourseActiveUseCase {

  @Autowired
  private CoursesRepository coursesRepository;

  public CourseEntity execute(UUID courseId) {
    CourseEntity courseEntity = this.coursesRepository.findById(courseId)
      .orElseThrow(() -> {
        throw new CourseNotFoundException();
      });

    courseEntity.toggleActive();

    return this.coursesRepository.save(courseEntity);
  }

}
