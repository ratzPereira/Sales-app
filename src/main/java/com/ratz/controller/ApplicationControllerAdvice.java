package com.ratz.controller;


import com.ratz.errors.ApiErrors;
import com.ratz.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleCustomExceptions(CustomException exception){
        String errorMessage = exception.getMessage();
        return new ApiErrors(errorMessage);
    }

}
