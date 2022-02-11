package br.com.forum.exception;

import br.com.forum.exception.curso.NotFoundCursoException;
import br.com.forum.exception.topico.NotfoundTopicoException;
import br.com.forum.exception.validation.ValidatationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class HandlerExceptionConfig {

    @Autowired
    private MessageSource messageSource; // mensagem por idioma

    @ExceptionHandler(NotFoundCursoException.class)
    public ResponseEntity<StandardError> notFoundCurso(NotFoundCursoException ex, HttpServletRequest request){
        StandardError error =
                new StandardError(ex.getMessage(), request.getRequestURI(),
                        HttpStatus.NOT_FOUND, System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(NotfoundTopicoException.class)
    public ResponseEntity<StandardError> notFoundTopico(NotfoundTopicoException ex, HttpServletRequest request){
        StandardError error =
                new StandardError(ex.getMessage(), request.getRequestURI(),
                        HttpStatus.NOT_FOUND, System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> notValidationException(MethodArgumentNotValidException e, HttpServletRequest request){
        ValidatationError error =
                new ValidatationError("Error validation", request.getRequestURI(), HttpStatus.BAD_REQUEST, System.currentTimeMillis());

        e.getFieldErrors().parallelStream().forEach(field -> {
            error.addError(field.getField(), messageSource.getMessage(field, LocaleContextHolder.getLocale()));
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> notValidationException(DataIntegrityViolationException ex, HttpServletRequest request){
        StandardError error =
                new StandardError(ex.getCause().getCause().getMessage(), request.getRequestURI(),
                        HttpStatus.BAD_REQUEST, System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<StandardError> requestParameterException(MissingServletRequestParameterException ex, HttpServletRequest request){
        ValidatationError error =
                new ValidatationError("Request parameter", request.getRequestURI(), HttpStatus.BAD_REQUEST, System.currentTimeMillis());

       error.addError(ex.getParameterName(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }



}
