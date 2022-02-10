package br.com.forum.dto.topico;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class TopicoAtualizar {
    @NotBlank
    @Length(min = 5, max = 150)
    private String titulo;
    @NotBlank
    private String mensagem;
}
