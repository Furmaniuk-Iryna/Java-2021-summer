package com.example.final_project.exception;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalJavaExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity nullExceptions(){
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity noSuchElementExceptions(){
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(SQLGrammarException.class)
    public ResponseEntity sqlExceptions(){
        return new ResponseEntity(HttpStatus.CONFLICT);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity runtimeExceptions(){
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity error(){
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
