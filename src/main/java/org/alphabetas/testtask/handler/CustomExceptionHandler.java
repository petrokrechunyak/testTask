package org.alphabetas.testtask.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<Map<String, String>> validation(MethodArgumentNotValidException ex) {

        Map<String, String> errorMessage = new LinkedHashMap<>();
        ex.getFieldErrors().forEach(x -> errorMessage.put(x.getField(), x.getDefaultMessage()));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Map<String, String>> illegalArgument(Exception ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Map<String, String>> anyError(Exception ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", "Oops, something went wrong!");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
