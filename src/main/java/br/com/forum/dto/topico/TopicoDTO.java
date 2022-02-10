package br.com.forum.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TopicoDTO {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
}
