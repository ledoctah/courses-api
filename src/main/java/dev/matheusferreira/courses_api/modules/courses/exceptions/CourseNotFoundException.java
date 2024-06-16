package dev.matheusferreira.courses_api.modules.courses.exceptions;

import org.springframework.http.HttpStatus;

import dev.matheusferreira.courses_api.exceptions.BusinessErrorException;

public class CourseNotFoundException extends BusinessErrorException {
  
  public CourseNotFoundException() {
    super("O curso não foi encontrado", HttpStatus.NOT_FOUND);
  }

}
