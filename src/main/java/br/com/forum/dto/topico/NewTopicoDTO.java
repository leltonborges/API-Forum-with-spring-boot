package br.com.forum.dto.topico;

import lombok.Data;

@Data
public class NewTopicoDTO {

    private String titulo;
    private String mensagem;
    private String curso;
}
