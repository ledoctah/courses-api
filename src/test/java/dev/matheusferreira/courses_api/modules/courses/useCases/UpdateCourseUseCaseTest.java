package dev.matheusferreira.courses_api.modules.courses.useCases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
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

import dev.matheusferreira.courses_api.modules.courses.dtos.UpdateCourseDTO;
import dev.matheusferreira.courses_api.modules.courses.entities.CourseEntity;
import dev.matheusferreira.courses_api.modules.courses.exceptions.CourseAlreadyExistsException;
import dev.matheusferreira.courses_api.modules.courses.exceptions.CourseNotFoundException;
import dev.matheusferreira.courses_api.modules.courses.repositories.CoursesRepository;

@ExtendWith(MockitoExtension.class)
public class UpdateCourseUseCaseTest {
  
  @InjectMocks
  private UpdateCourseUseCase updateCourseUseCase;

  @Mock
  private CoursesRepository coursesRepository;

  @Test
  @DisplayName("Should be able to update a course")
  public void shouldBeAbleToUpdateCourse() {
    var courseId = UUID.randomUUID();
      
    var courseEntity = CourseEntity.builder()
      .category("Software Development")
      .name("Java Course")
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .active(true)
      .id(courseId)
      .build();

    var updateCourseDTO = UpdateCourseDTO.builder()
      .category("Software Development")
      .name("Javascript Course")
      .id(courseId)
      .build();

    when(coursesRepository.findById(courseId)).thenReturn(Optional.of(courseEntity));
    when(coursesRepository.save(courseEntity)).thenReturn(courseEntity);

    var result = updateCourseUseCase.execute(updateCourseDTO);

    assertEquals(result.getName(), updateCourseDTO.getName());
    assertEquals(result.getCategory(), updateCourseDTO.getCategory());
  }

  @Test
  @DisplayName("Should not be able to update the category if it is not provided on the input")
  public void shouldNotUpdateTheCategoryIfItIsNotProvided() {
    var category = "Software Development";
    var name = "Java Course";
    var courseId = UUID.randomUUID();
      
    var courseEntity = CourseEntity.builder()
      .category(category)
      .name(name)
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .active(true)
      .id(courseId)
      .build();

    var updateCourseDTO = UpdateCourseDTO.builder()
      .category(null)
      .name("Javascript Course")
      .id(courseId)
      .build();

    when(coursesRepository.findById(courseId)).thenReturn(Optional.of(courseEntity));
    when(coursesRepository.save(courseEntity)).thenReturn(courseEntity);

    var result = updateCourseUseCase.execute(updateCourseDTO);

    assertEquals(result.getName(), updateCourseDTO.getName());
    assertEquals(result.getCategory(), category);
  }

  @Test
  @DisplayName("Should not be able to update the name if it is not provided on the input")
  public void shouldNotUpdateTheNameIfItIsNotProvided() {
    var category = "Software Development";
    var name = "Java Course";
    var courseId = UUID.randomUUID();
      
    var courseEntity = CourseEntity.builder()
      .category(category)
      .name(name)
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .active(true)
      .id(courseId)
      .build();

    var updateCourseDTO = UpdateCourseDTO.builder()
      .category("Web Development")
      .name(null)
      .id(courseId)
      .build();

    when(coursesRepository.findById(courseId)).thenReturn(Optional.of(courseEntity));
    when(coursesRepository.save(courseEntity)).thenReturn(courseEntity);

    var result = updateCourseUseCase.execute(updateCourseDTO);

    assertEquals(result.getName(), name);
    assertEquals(result.getCategory(), updateCourseDTO.getCategory());
  }

  @Test
  @DisplayName("Should not be able to update a course if it does not exists")
  public void shouldNotBeAbleToUpdateCourseIfItDoesNotExists() {
    var courseId = UUID.randomUUID();

    when(coursesRepository.findById(courseId)).thenReturn(Optional.empty());

    var updateCourseDTO = UpdateCourseDTO.builder()
      .category("Software Development")
      .name("Javascript Course")
      .id(courseId)
      .build();

    try {
      updateCourseUseCase.execute(updateCourseDTO);
    } catch (Exception e) {
      assertThat(e).isInstanceOf(CourseNotFoundException.class);
    }

  }

  @Test
  @DisplayName("Should not be able to update a course if the combination of name and category conflicts with another course that already exists")
  public void shouldNotBeAbleToUpdateCourseIfItConflictsWithAnExistingOne() {
    var courseId = UUID.randomUUID();
      
    var courseEntity = CourseEntity.builder()
      .category("Software Development")
      .name("Java Course")
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .active(true)
      .id(courseId)
      .build();
      
    var alreadyExistingCourse = CourseEntity.builder()
      .category("Software Development")
      .name("Javascript Course")
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .active(true)
      .id(UUID.randomUUID())
      .build();

    var updateCourseDTO = UpdateCourseDTO.builder()
      .category("Software Development")
      .name("Javascript Course")
      .id(courseId)
      .build();

    when(coursesRepository.findById(courseId)).thenReturn(Optional.of(courseEntity));

    when(
      coursesRepository
        .findByNameAndCategoryAndIdNot(
          updateCourseDTO.getName(),
          updateCourseDTO.getCategory(),
          updateCourseDTO.getId()
        )
    ).thenReturn(Optional.of(alreadyExistingCourse));

    try {
      updateCourseUseCase.execute(updateCourseDTO);
    } catch (Exception e) {
      assertThat(e).isInstanceOf(CourseAlreadyExistsException.class);
    }

  }

}
