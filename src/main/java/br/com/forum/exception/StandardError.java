package br.com.forum.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class StandardError {
    private String error;
    private String path;
    private HttpStatus status;
    private Long timestamp;
}
