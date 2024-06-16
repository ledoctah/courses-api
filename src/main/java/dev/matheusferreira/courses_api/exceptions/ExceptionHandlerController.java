package dev.matheusferreira.courses_api.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

  private MessageSource messageSource;

  public ExceptionHandlerController(MessageSource messageSource) {
    this.messageSource = messageSource;
  }
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    ExceptionDTO dto = ExceptionDTO.builder()
      .status(HttpStatus.BAD_REQUEST)
      .message("Validation error")
      .build();
    
    List<Object> errorMessagesDto = new ArrayList<>();

    e.getBindingResult().getFieldErrors().forEach(err -> {
      String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());

      ErrorMessageDTO errorMessageDTO = ErrorMessageDTO.builder()
        .field(err.getField())
        .message(message)
        .build();

      errorMessagesDto.add(errorMessageDTO);
    });

    dto.setDetails(errorMessagesDto);

    return ResponseEntity.badRequest().body(dto);
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException e, HttpServletRequest request) {
    String message = String.format(
      "O caminho %s é inválido",
      request.getRequestURI().toString()
    );
    
    ExceptionDTO dto = ExceptionDTO.builder()
      .message(message)
      .build();
    
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleInternalServerError(Exception e) {
    e.printStackTrace();

    ExceptionDTO dto = ExceptionDTO.builder()
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .message("Internal Server Error")
      .build();

    return ResponseEntity.internalServerError().body(dto);
  }

}
