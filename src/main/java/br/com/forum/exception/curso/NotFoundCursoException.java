package br.com.forum.exception.curso;

public class NotFoundCursoException extends RuntimeException{
    public NotFoundCursoException(String message) {
        super(message);
    }

    public NotFoundCursoException(String message, Throwable cause) {
        super(message, cause);
    }
}
