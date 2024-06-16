package dev.matheusferreira.courses_api.modules.courses.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseDTO {
  
  @NotEmpty(message = "O campo [name] não deve ser vazio")
  @Length(min = 1, max = 512, message = "O campo [name] deve ter entre 1 e 512 caracteres")
  String name;
  
  @NotEmpty(message = "O campo [category] não deve ser vazio")
  @Length(min = 1, max = 255, message = "O campo [category] deve ter entre 1 e 255 caracteres")
  String category;

}
