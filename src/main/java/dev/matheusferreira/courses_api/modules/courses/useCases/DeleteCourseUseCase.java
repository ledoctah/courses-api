package dev.matheusferreira.courses_api.modules.courses.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.matheusferreira.courses_api.modules.courses.entities.CourseEntity;
import dev.matheusferreira.courses_api.modules.courses.exceptions.CourseNotFoundException;
import dev.matheusferreira.courses_api.modules.courses.repositories.CoursesRepository;

@Service
public class DeleteCourseUseCase {
  
  @Autowired
  private CoursesRepository coursesRepository;

  public void execute(UUID courseId) {
    CourseEntity courseEntity = this.coursesRepository.findById(courseId)
      .orElseThrow(() -> {
        throw new CourseNotFoundException();
      });

    this.coursesRepository.delete(courseEntity);
  }

}
