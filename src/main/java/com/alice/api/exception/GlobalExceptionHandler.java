package com.alice.api.exception;

import com.alice.api.dto.ResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
//  ResponseEntity<String> handleRuntimeException(RuntimeException e) {
//    return ResponseEntity.badRequest().body(e.getMessage());
//  }
  ResponseDTO<String> handleRuntimeException(RuntimeException e) {
    return ResponseDTO.<String>builder()
        .data(e.getMessage())
        .status(400)
        .message("Validation error!")
        .build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
//  ResponseEntity<String> handleMethodNotAllowedException(MethodArgumentNotValidException e) {
//    return ResponseEntity.badRequest().body(e.getFieldError().getDefaultMessage());
//  }
  ResponseDTO<String> handleMethodNotAllowedException(MethodArgumentNotValidException e) {
    return ResponseDTO.<String>builder()
        .data(e.getFieldError().getDefaultMessage())
        .status(400)
        .message("Validation error!")
        .build();
  }

  @ExceptionHandler(EntityNotFoundException.class)
  ResponseDTO<String> handleEntityNotFoundException(EntityNotFoundException e) {
    return ResponseDTO.<String>builder()
        .data(e.getMessage())
        .status(400)
        .message("Null!")
        .build();
  }


}
