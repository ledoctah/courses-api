package dev.matheusferreira.courses_api.modules.courses.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "courses")
@DynamicUpdate
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  UUID id;

  @CreationTimestamp
  @Column(name = "created_at")
  LocalDateTime createdAt;
  
  @UpdateTimestamp
  @Column(name = "updated_at")
  LocalDateTime updatedAt;

  @NotNull
  @Length(min = 1, max = 512)
  String name;

  @NotNull
  @Length(min = 1, max = 255)
  String category;

  @NotNull
  boolean active;

}
