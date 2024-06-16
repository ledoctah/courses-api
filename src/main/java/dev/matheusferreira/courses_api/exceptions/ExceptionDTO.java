package dev.matheusferreira.courses_api.exceptions;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDTO {
  
  @Builder.Default
  LocalDateTime timestamp = LocalDateTime.now(ZoneOffset.UTC);
  
  @Builder.Default
  HttpStatus status = HttpStatus.BAD_REQUEST;

  String message;

  List<Object> details;

}
