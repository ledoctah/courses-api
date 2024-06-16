package dev.matheusferreira.courses_api.modules.courses.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.matheusferreira.courses_api.modules.courses.entities.CourseEntity;

public interface CoursesRepository extends JpaRepository<CourseEntity, UUID> {
  List<CourseEntity> findByCategoryAndNameContainingIgnoreCase(String category, String name);

  Optional<CourseEntity> findByNameAndCategory(String name, String category);

  Optional<CourseEntity> findByNameAndCategoryAndIdNot(String name, String category, UUID id);
}
