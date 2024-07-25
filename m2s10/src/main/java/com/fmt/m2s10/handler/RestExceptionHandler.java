package com.fmt.m2s10.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fmt.m2s10.domain.exception.ResourceBadRequestException;
import com.fmt.m2s10.domain.exception.ResourceNotFoundException;
import com.fmt.m2s10.domain.model.ErrorResponse;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerNotFound(ResourceNotFoundException ex) {
				
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(ResourceBadRequestException.class)
	public ResponseEntity<ErrorResponse> handlerBadRequest(ResourceBadRequestException ex) {
		
		ErrorResponse erro = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage());
		
		return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handlerGenericException(Exception ex) {
		
		ErrorResponse erro = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", ex.getMessage());
		
		return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}
