package dev.matheusferreira.courses_api.modules.courses.useCases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.matheusferreira.courses_api.modules.courses.dtos.ListCoursesDTO;
import dev.matheusferreira.courses_api.modules.courses.entities.CourseEntity;
import dev.matheusferreira.courses_api.modules.courses.repositories.CoursesRepository;

@ExtendWith(MockitoExtension.class)
public class ListCoursesUseCaseTest {
  
  @InjectMocks
  private ListCoursesUseCase listCoursesUseCase;

  @Mock
  private CoursesRepository coursesRepository;

  @Test
  @DisplayName("Should be able to list courses by category and name")
  public void shouldBeAbleToListCoursesByCategoryAndName() {
    var category = "Software Development";
    var name = "Java Course";

    var listCoursesDTO = ListCoursesDTO.builder()
      .category(category)
      .name(name)
      .build();
      
    var courseEntity = CourseEntity.builder()
      .category(category)
      .name(name)
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .active(true)
      .id(UUID.randomUUID())
      .build();

    var coursesList = List.of(courseEntity);
      
    when(coursesRepository
      .findByCategoryAndNameContainingIgnoreCase(category, name))
      .thenReturn(coursesList);

    var result = this.listCoursesUseCase.execute(listCoursesDTO);

    assertEquals(result.size(), 1);
    assertEquals(result.get(0).getId(), courseEntity.getId());
  }

  @Test
  @DisplayName("Should return an empty list if no courses were found")
  public void shouldReturnAnEmptyListIfNoCoursesWereFound() {
    var category = "Software Development";
    var name = "Java Course";

    var listCoursesDTO = ListCoursesDTO.builder()
      .category(category)
      .name(name)
      .build();

    List<CourseEntity> coursesList = new ArrayList<>();
      
    when(coursesRepository.findByCategoryAndNameContainingIgnoreCase(category, name))
      .thenReturn(coursesList);

    var result = this.listCoursesUseCase.execute(listCoursesDTO);

    assertEquals(result.size(), 0);
  }

}
