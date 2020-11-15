package com.example.springsocial.config;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	private final Logger LOGGER = Logger.getLogger(CustomExceptionHandler.class);

	@ResponseStatus(code  = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<?> parentExceptionForInterServerError(Exception exception) {
		LOGGER.error("error",exception);
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", exception.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> NnullPointerExceptionHandeler(NullPointerException exception) {
		LOGGER.error("error",exception);
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Null pointer");
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
