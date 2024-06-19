package dev.matheusferreira.courses_api.modules.courses.useCases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import dev.matheusferreira.courses_api.modules.courses.entities.CourseEntity;
import dev.matheusferreira.courses_api.modules.courses.exceptions.CourseNotFoundException;
import dev.matheusferreira.courses_api.modules.courses.repositories.CoursesRepository;

@ExtendWith(MockitoExtension.class)
public class DeleteCourseUseCaseTest {
  
  @InjectMocks
  private DeleteCourseUseCase deleteCourseUseCase;

  @Mock
  private CoursesRepository coursesRepository;

  @Test
  @DisplayName("Should be able to delete a course")
  public void shouldBeAbleToDeleteCourse() {
    var courseId = UUID.randomUUID();
      
    var courseEntity = CourseEntity.builder()
      .category("Software Development")
      .name("Java Course")
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .active(true)
      .id(courseId)
      .build();

    when(coursesRepository.findById(courseId)).thenReturn(Optional.of(courseEntity));

    deleteCourseUseCase.execute(courseId);

    verify(coursesRepository, times(1)).delete(courseEntity);
  }

  @Test
  @DisplayName("Should not be able to delete a course if it does not exists")
  public void shouldNotBeAbleToDeleteCourseIfItDoesNotExists() {
    var courseId = UUID.randomUUID();

    when(coursesRepository.findById(courseId)).thenReturn(Optional.empty());

    try {
      deleteCourseUseCase.execute(courseId);
    } catch (Exception e) {
      assertThat(e).isInstanceOf(CourseNotFoundException.class);
    }

  }

}
