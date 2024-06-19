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

import dev.matheusferreira.courses_api.modules.courses.entities.CourseEntity;
import dev.matheusferreira.courses_api.modules.courses.exceptions.CourseNotFoundException;
import dev.matheusferreira.courses_api.modules.courses.repositories.CoursesRepository;

@ExtendWith(MockitoExtension.class)
public class ToggleCourseActiveUseCaseTest {
  
  @InjectMocks
  private ToggleCourseActiveUseCase toggleCourseActiveUseCase;

  @Mock
  private CoursesRepository coursesRepository;

  @Test
  @DisplayName("Should be able to activate a disabled course")
  public void shouldBeAbleToActivateCourse() {
    var courseId = UUID.randomUUID();
      
    var courseEntity = CourseEntity.builder()
      .category("Software Development")
      .name("Java Course")
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .active(false)
      .id(courseId)
      .build();

    when(coursesRepository.findById(courseId)).thenReturn(Optional.of(courseEntity));
    when(coursesRepository.save(any(CourseEntity.class))).thenReturn(courseEntity);

    var result = this.toggleCourseActiveUseCase.execute(courseId);

    assertEquals(result.isActive(), true);
  }

  @Test
  @DisplayName("Should be able to disable a active course")
  public void shouldBeAbleToDisableCourse() {
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
    when(coursesRepository.save(any(CourseEntity.class))).thenReturn(courseEntity);

    var result = this.toggleCourseActiveUseCase.execute(courseId);

    assertEquals(result.isActive(), false);
  }

  @Test
  @DisplayName("Should not be able to toggle a course that does not exists")
  public void shouldNotBeAbleToToggleACourseThatDoesNotExists() {
    when(coursesRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

    try {
      this.toggleCourseActiveUseCase.execute(UUID.randomUUID());
    } catch (Exception e) {
      assertThat(e).isInstanceOf(CourseNotFoundException.class);
    }
  }

}
