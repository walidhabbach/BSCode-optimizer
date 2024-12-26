package org.gateway.exception;
import org.gateway.exception.wrapper.ErrorResponse;
import org.gateway.exception.wrapper.JwtValidationException;
import org.gateway.exception.wrapper.ResourceAlreadyExistException;
import org.gateway.exception.wrapper.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(
			ResourceNotFoundException ex, WebRequest request) {
		ErrorResponse errorDetails =   ErrorResponse.builder()
						.error("INTERNAL_SERVER_ERROR")
								.message(ex.getMessage())
										.status(HttpStatus.valueOf(HttpStatus.NOT_FOUND.value()))
				.details(request.getDescription(false))
						.timestamp(LocalDateTime.now())
								.build();

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<?> handleResourceAlreadyExistException(
			ResourceNotFoundException ex, WebRequest request) {
		ErrorResponse errorDetails =   ErrorResponse.builder()
				.error("ResourceAlreadyExistException")
				.message(ex.getMessage())
				.status(HttpStatus.valueOf(	HttpStatus.CONFLICT.value()))
				.details(request.getDescription(false))
				.timestamp(LocalDateTime.now())
				.build();
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}
	@ExceptionHandler(JwtValidationException.class)
	public ResponseEntity<?> handleJwtValidationException(
			JwtValidationException ex, WebRequest request) {
		ErrorResponse errorDetails = ErrorResponse.builder()
				.error("JWT_VALIDATION_ERROR")
				.message(ex.getMessage())
				.status(HttpStatus.valueOf(	HttpStatus.UNAUTHORIZED.value()))
				.details(request.getDescription(false))
				.timestamp(LocalDateTime.now())
				.build();
		return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(
			Exception ex, WebRequest request) {
		ErrorResponse errorDetails =   ErrorResponse.builder()
				.error("INTERNAL_SERVER_ERROR")
				.message(ex.getMessage())
				.status(HttpStatus.valueOf(	HttpStatus.INTERNAL_SERVER_ERROR.value()))
				.details(request.getDescription(false))
				.timestamp(LocalDateTime.now())
				.build();
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}