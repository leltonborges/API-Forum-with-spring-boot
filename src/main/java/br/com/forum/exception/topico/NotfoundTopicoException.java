package br.com.forum.exception.topico;

public class NotfoundTopicoException extends RuntimeException {
    private static final long serialVersionUID = 4173397992210508693L;

    public NotfoundTopicoException(String message) {
        super(message);
    }

    public NotfoundTopicoException(String message, Throwable cause) {
        super(message, cause);
    }
}
