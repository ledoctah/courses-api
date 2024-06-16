package dev.matheusferreira.courses_api.modules.courses.dtos;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

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

  UUID id;
  
  @Length(min = 1, max = 512, message = "O campo [name] deve ter entre 1 e 512 caracteres")
  @Nullable()
  String name;
  
  @Length(min = 1, max = 255, message = "O campo [category] deve ter entre 1 e 255 caracteres")
  @Nullable()
  String category;

}
