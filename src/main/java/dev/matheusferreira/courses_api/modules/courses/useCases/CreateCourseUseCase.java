package dev.matheusferreira.courses_api.modules.courses.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.matheusferreira.courses_api.modules.courses.dtos.CreateCourseDTO;
import dev.matheusferreira.courses_api.modules.courses.entities.CourseEntity;
import dev.matheusferreira.courses_api.modules.courses.exceptions.CourseAlreadyExistsException;
import dev.matheusferreira.courses_api.modules.courses.repositories.CoursesRepository;

@Service
public class CreateCourseUseCase {
  
  @Autowired
  private CoursesRepository coursesRepository;

  public CourseEntity execute(CreateCourseDTO createCourseDTO) {
    this.coursesRepository.findByNameAndCategory(
      createCourseDTO.getName(),
      createCourseDTO.getCategory()
    ).ifPresent((course) -> {
      throw new CourseAlreadyExistsException();
    });

    CourseEntity courseEntity = CourseEntity.builder()
      .name(createCourseDTO.getName())
      .category(createCourseDTO.getCategory())
      .active(true)
      .build();

    return this.coursesRepository.save(courseEntity);
  }

}
