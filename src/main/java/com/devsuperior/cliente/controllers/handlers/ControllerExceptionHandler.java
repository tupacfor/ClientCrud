package com.devsuperior.cliente.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.cliente.dtos.CustomError;
import com.devsuperior.cliente.dtos.ValidationError;
import com.devsuperior.cliente.exceptions.ExceptionResourceNotFound;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
	@ExceptionHandler(ExceptionResourceNotFound.class)
	public ResponseEntity<CustomError> resourceNotFound(ExceptionResourceNotFound e, HttpServletRequest request)  {
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustomError error = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> notValidField(MethodArgumentNotValidException e, HttpServletRequest request)  {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError error = new ValidationError(Instant.now(), status.value(), "Invalid fields", request.getRequestURI());
		for(FieldError f : e.getBindingResult().getFieldErrors()) {
			error.addErros(f.getField(), f.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(error);
	}
}
