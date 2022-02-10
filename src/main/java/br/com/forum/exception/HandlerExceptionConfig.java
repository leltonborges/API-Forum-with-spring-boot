package br.com.forum.exception;

import br.com.forum.exception.curso.NotFoundCursoException;
import br.com.forum.exception.validation.ValidatationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class HandlerExceptionConfig {


    @ExceptionHandler(NotFoundCursoException.class)
    public ResponseEntity<StandardError> notFoundCurso(NotFoundCursoException ex, HttpServletRequest request){
        StandardError error =
                new StandardError(ex.getMessage(), request.getRequestURI(),
                        HttpStatus.NOT_FOUND, System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> notValidationException(MethodArgumentNotValidException e, HttpServletRequest request){
        ValidatationError error =
                new ValidatationError("Error validation", request.getRequestURI(), HttpStatus.UNPROCESSABLE_ENTITY, System.currentTimeMillis());

        for(FieldError field : e.getFieldErrors()){
            error.addError(field.getField(), field.getDefaultMessage());
        }
        System.out.println("\n\naqui\n\n");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
