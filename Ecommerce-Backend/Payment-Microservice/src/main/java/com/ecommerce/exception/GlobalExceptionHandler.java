package com.ecommerce.exception;

import com.ecommerce.dto.response.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFound(
            ResourceNotFoundException exception,
            HttpServletRequest request
    ) {


        ExceptionResponse response =
                ExceptionResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .error("Resource Not Found")
                        .message(exception.getMessage())
                        .path(request.getRequestURI())
                        .build();


        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );

    }



    @ExceptionHandler(PaymentFailedException.class)
    public ResponseEntity<ExceptionResponse> handlePaymentFailed(
            PaymentFailedException exception,
            HttpServletRequest request
    ) {


        ExceptionResponse response =
                ExceptionResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error("Payment Failed")
                        .message(exception.getMessage())
                        .path(request.getRequestURI())
                        .build();


        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );

    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {


        Map<String, String> errors = new HashMap<>();


        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );


        ExceptionResponse response =
                ExceptionResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error("Validation Failed")
                        .message(errors.toString())
                        .path(request.getRequestURI())
                        .build();


        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );

    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGlobalException(
            Exception exception,
            HttpServletRequest request
    ) {


        ExceptionResponse response =
                ExceptionResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .error("Internal Server Error")
                        .message(exception.getMessage())
                        .path(request.getRequestURI())
                        .build();


        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR
        );

    }

}