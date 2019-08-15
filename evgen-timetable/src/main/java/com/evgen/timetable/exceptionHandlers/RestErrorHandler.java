package com.evgen.timetable.exceptionHandlers;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.evgen.timetable.exception.NotFoundException;

@CrossOrigin
@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {NotFoundException.class})
  protected final ResponseEntity<Object> handleNotFoundException() {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(value = {DataAccessException.class})
  protected final ResponseEntity<Object> handleDataAccessException(final DataAccessException ex, final WebRequest request) {
    final ApiError apiError = message(ex);
    return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
  protected final ResponseEntity<Object> handleIllegalException(final NotFoundException ex, final WebRequest request) {
    final ApiError apiError = message(ex);
    return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    ValidationErrorResponse error = new ValidationErrorResponse();
    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
      error.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
    }
    return ResponseEntity.badRequest().body(error);
  }

  private ApiError message(final Exception ex) {
    final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
    final String devMessage = ex.getClass().getSimpleName();

    return ApiError.builder()
        .message(message)
        .developerMessage(devMessage)
        .build();
  }
}