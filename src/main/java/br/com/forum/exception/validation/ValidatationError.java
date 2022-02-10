package br.com.forum.exception.validation;

import br.com.forum.exception.StandardError;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidatationError extends StandardError implements Serializable {
    private static final long serialVersionUID = -3658809809031217813L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidatationError(String error, String path, HttpStatus status, Long timestamp) {
        super(error, path, status, timestamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String field, String message){
        errors.add(new FieldMessage(field, message));
    }
}
