package com.nativa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

   @ExceptionHandler(BadRequestException.class)
   public ResponseEntity<StandardError> badException(BadRequestException e, HttpServletRequest req) {
       StandardError error = new StandardError(HttpStatus.BAD_REQUEST, "Bad Request", e.getMessage(),
               req.getRequestURI());
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
   }
}
