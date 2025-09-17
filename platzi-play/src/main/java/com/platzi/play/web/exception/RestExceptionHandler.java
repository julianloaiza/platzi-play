package com.platzi.play.web.exception;

import com.platzi.play.domain.exception.MovieAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    // Personalized Exceptions
    @ExceptionHandler(MovieAlreadyExistsException.class)
    public ResponseEntity<Error> handleException(MovieAlreadyExistsException exception) {
        Error error = new Error("movie-already-exists", exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    // Validation Exceptions (DTOs)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleException(MethodArgumentNotValidException exception) {
        List<Error> errors = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            errors.add(new Error(fieldError.getField(), fieldError.getDefaultMessage()));
        });

        return ResponseEntity.badRequest().body(errors);
    }

    // General Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception exception){
        Error error = new Error("unknown-error", exception.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}
