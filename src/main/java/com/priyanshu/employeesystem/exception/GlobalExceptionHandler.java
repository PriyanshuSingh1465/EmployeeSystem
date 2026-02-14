package com.priyanshu.employeesystem.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.priyanshu.employeesystem.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(ResourceNotFoundException ex) {

	    ApiResponse<Object> response = ApiResponse.builder()
	            .success(false)
	            .message(ex.getMessage())
	            .data(null)
	            .timestamp(LocalDateTime.now())
	            .build();

	    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(RuntimeException ex) {
        return ResponseEntity.badRequest().body(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "error", ex.getMessage()
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "error", "Something went wrong"
                )
        );
    }

}
