package br.com.forum.exception.curso;

import java.io.Serializable;

public class NotFoundCursoException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 7410814765087155503L;

    public NotFoundCursoException(String message) {
        super(message);
    }

    public NotFoundCursoException(String message, Throwable cause) {
        super(message, cause);
    }
}
