package com.pinsoft.internship.recipeshare.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.dao.InvalidDataAccessResourceUsageException;


@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<ApiException> handleApiRequestException(ApiRequestException e){
        return new ApiException(  e.getMessage(), HttpStatus.BAD_REQUEST  ).toResponse();
    }
    @ExceptionHandler(value = {InvalidDataAccessResourceUsageException.class})
    public ResponseEntity<ApiException> handleInvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e){
        return new ApiException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR).toResponse();
    }
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ApiException> handleIllegalArgumentException(IllegalArgumentException e){
        return new ApiException(e.getMessage(),HttpStatus.BAD_REQUEST).toResponse();
    }
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ApiException> handleException(Exception e){
        return new ApiException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR).toResponse();
    }
}
