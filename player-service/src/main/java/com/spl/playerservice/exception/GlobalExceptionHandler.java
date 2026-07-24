package com.spl.playerservice.exception;
import com.spl.commonlibrary.dto.ApiResponse;

import com.spl.commonlibrary.exception.BadRequestException;
import com.spl.commonlibrary.exception.DuplicateResourceException;
import com.spl.commonlibrary.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> myMethodArguementNotValidException(MethodArgumentNotValidException ex) {
        Map<String,String> response=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err->{

            String fieldName=((FieldError)err).getField();
            String message=err.getDefaultMessage();
            response.put(fieldName,message);
        });
        return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFOundException(ResourceNotFoundException ex) {
        ApiResponse apiResponse = ApiResponse.builder()
                .success("false")
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse> handleDuplicateResourceException(DuplicateResourceException ex) {
        ApiResponse apiResponse=ApiResponse.builder()
                .success("false")
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse> handleBadRequest(BadRequestException ex) {
        ApiResponse apiResponse=ApiResponse.builder()
                .success("false")
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
