package dev.matheusferreira.courses_api.modules.courses.useCases;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.matheusferreira.courses_api.modules.courses.dtos.CreateCourseDTO;
import dev.matheusferreira.courses_api.modules.courses.entities.CourseEntity;
import dev.matheusferreira.courses_api.modules.courses.exceptions.CourseAlreadyExistsException;
import dev.matheusferreira.courses_api.modules.courses.repositories.CoursesRepository;

@ExtendWith(MockitoExtension.class)
public class CreateCourseUseCaseTest {
  
  @InjectMocks
  private CreateCourseUseCase createCourseUseCase;

  @Mock
  private CoursesRepository coursesRepository;

  @Test
  @DisplayName("Should be able to create a course")
  public void shouldBeAbleToCreateCourse() {
    var courseId = UUID.randomUUID();

    var createCourseDTO = CreateCourseDTO.builder()
      .category("Software Development")
      .name("Java Course")
      .build();
      
    var courseEntity = CourseEntity.builder()
      .category(createCourseDTO.getCategory())
      .name(createCourseDTO.getName())
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .active(true)
      .id(courseId)
      .build();

    when(coursesRepository.save(any(CourseEntity.class))).thenReturn(courseEntity);

    var result = this.createCourseUseCase.execute(createCourseDTO);

    assertEquals(result.getId(), courseId);
  }

  @Test
  @DisplayName("Should not be able to create a course if an course with the same name and category already exists")
  public void shouldNotBeAbleToCreateAnAlreadyExistingCourse() {
    var category = "Software Development";
    var name = "Java Course";

    var createCourseDTO = CreateCourseDTO.builder()
      .category(category)
      .name(name)
      .build();
      
    var courseEntity = CourseEntity.builder()
      .category(createCourseDTO.getCategory())
      .name(createCourseDTO.getName())
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .active(true)
      .id(UUID.randomUUID())
      .build();

    when(coursesRepository.findByNameAndCategory(name, category))
      .thenReturn(Optional.of(courseEntity));

    try {
      this.createCourseUseCase.execute(createCourseDTO);
    } catch (Exception e) {
      assertThat(e).isInstanceOf(CourseAlreadyExistsException.class);
    }
  }

}
