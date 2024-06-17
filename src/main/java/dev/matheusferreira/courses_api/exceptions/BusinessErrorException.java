package dev.matheusferreira.courses_api.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BusinessErrorException extends RuntimeException {
  
  private HttpStatus httpStatus;

  public BusinessErrorException(String message, HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }

}
