package dev.matheusferreira.courses_api.modules.courses.dtos;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCourseDTO {

  @Schema(description = "ID do curso", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
  UUID id;
  
  @Length(min = 1, max = 512, message = "O campo [name] deve ter entre 1 e 512 caracteres")
  @Nullable()
  @Schema(description = "Nome do curso", example = "Curso de Java")
  String name;
  
  @Length(min = 1, max = 255, message = "O campo [category] deve ter entre 1 e 255 caracteres")
  @Nullable()
  @Schema(description = "Categoria do curso", example = "Desenvolvimento de Software")
  String category;

}
