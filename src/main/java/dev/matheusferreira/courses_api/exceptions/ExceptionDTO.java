package dev.matheusferreira.courses_api.exceptions;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
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
  @Schema(description = "Data/hora de quando ocorreu a exceção")
  LocalDateTime timestamp = LocalDateTime.now(ZoneOffset.UTC);
  
  @Builder.Default
  @Schema(description = "Status HTTP do Erro", example = "BAD_REQUEST")
  HttpStatus status = HttpStatus.BAD_REQUEST;

  @Schema(description = "Mensagem do erro", example = "Erro de validação")
  String message;

  @Schema(description = "Lista de detalhes da exception, sendo esse valor um objeto que pode ser dinâmico e depende da rota", example = "[{\"field\":\"category\",\"message\":\"O campo [category] não deve ser vazio\"}]")
  List<Object> details;

}
