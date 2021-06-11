package com.nativa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ResourceExceptionHandler {

   @ExceptionHandler(BadRequestException.class)
   public ResponseEntity<StandardError> badException(BadRequestException e, HttpServletRequest req) {
       StandardError error = new StandardError(HttpStatus.BAD_REQUEST, "Bad Request", e.getMessage(),
               req.getRequestURI());
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
   }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> constraintViolation(ConstraintViolationException e, HttpServletRequest req) {

        e.getConstraintViolations().forEach(cv -> {
            System.out.println(cv);
        });
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST, "Bad Request", e.getMessage(),
                req.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> constraintViolation( MethodArgumentNotValidException e , HttpServletRequest req){


        StandardError error = new StandardError( HttpStatus.BAD_REQUEST, "Bad Request","campos Invalidos ou Faltando", req.getRequestURI());
        e.getFieldErrors().forEach( validError ->
        {
            error.getFieldsErros().add(new FieldsErros(validError.getField(), validError.getDefaultMessage()) );
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest req ){
        StandardError error = new StandardError( HttpStatus.NOT_FOUND, "Não encontrado", e.getMessage(), req.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
