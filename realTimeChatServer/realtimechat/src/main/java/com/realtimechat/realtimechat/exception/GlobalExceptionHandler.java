package com.realtimechat.realtimechat.exception;

import org.springframework.http.ResponseEntity;//ResponseEntity → used to send HTTP responses
import org.springframework.web.bind.MethodArgumentNotValidException;//thrown when @Valid fails
import org.springframework.web.bind.annotation.ExceptionHandler;//tells Spring: “call this method when X exception happens”
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice//global exception handler for all controllers
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        String msg=ex.getBindingResult().getFieldError().getDefaultMessage();
        Map<String,String> body=new HashMap<>();
        body.put("error",msg);
        //What this means:
        //Return HTTP 400 → invalid input
        //Body contains your error message
        //Frontend receives a clean JSON error
        return ResponseEntity.badRequest().body(body);
    }
}
