package dev.matheusferreira.courses_api.modules.courses.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "courses")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "category"}, name = "UniqueNameCategory")})
@DynamicUpdate
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Schema(description = "ID do curso", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
  UUID id;

  @CreationTimestamp
  @Column(name = "created_at")
  @Schema(description = "Data da criação do curso")
  LocalDateTime createdAt;
  
  @UpdateTimestamp
  @Column(name = "updated_at")
  @Schema(description = "Data da última atualização do curso")
  LocalDateTime updatedAt;

  @NotNull
  @Length(min = 1, max = 512)
  @Schema(description = "Nome do curso", example = "Curso de Java")
  String name;

  @NotNull
  @Length(min = 1, max = 255)
  @Schema(description = "Categoria do curso", example = "Desenvolvimento de Software")
  String category;

  @NotNull
  @Schema(description = "Parâmetro que define se o curso está ou não ativo", example = "true")
  boolean active;

  public void toggleActive() {
    this.active = !this.active;
  }

}
