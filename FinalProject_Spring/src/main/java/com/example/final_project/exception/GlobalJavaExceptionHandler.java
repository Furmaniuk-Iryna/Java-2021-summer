package com.example.final_project.exception;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

/**
 * GlobalJavaExceptionHandler is a class we'll be using to send a response if an error or exception occurs
 * Please see the {@link org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler} class for true identity
 */
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
    public ResponseEntity numberFormatException(){
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity exception(){
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Error.class)
    public ResponseEntity error(){
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
