package com.inventario.stock_flow.infrastructure.rest.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex,
                        HttpServletRequest request) {
                Map<String, String> errors = new HashMap<>();
                ex.getBindingResult().getFieldErrors()
                                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

                ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation error",
                                "Los datos enviados no son válidos", request.getRequestURI());

                return ResponseEntity.badRequest().body(response);
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ErrorResponse> handleBusinessExceptions(
                        IllegalArgumentException ex, HttpServletRequest request) {

                ErrorResponse response = new ErrorResponse(
                                HttpStatus.BAD_REQUEST.value(),
                                "Business Rule Violation",
                                ex.getMessage(),
                                request.getRequestURI());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleGeneralExceptions(
                        Exception ex, HttpServletRequest request) {

                ex.printStackTrace();

                ErrorResponse response = new ErrorResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "Internal Server Error",
                                "Ocurrió un error inesperado. Por favor, contacte a soporte.",
                                request.getRequestURI());
                return ResponseEntity.internalServerError().body(response);
        }
}
