package dev.matheusferreira.courses_api.modules.courses.exceptions;

import org.springframework.http.HttpStatus;

import dev.matheusferreira.courses_api.exceptions.BusinessErrorException;

public class CourseAlreadyExistsException extends BusinessErrorException {
  
  public CourseAlreadyExistsException() {
    super("Um curso com o mesmo nome e categoria já está cadastrado", HttpStatus.CONFLICT);
  }

}
