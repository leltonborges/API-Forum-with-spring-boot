package br.com.forum.dto.topico;

import br.com.forum.dto.resposta.RespostaDTO;
import br.com.forum.modelo.StatusTopico;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DetalhesTopico {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;
    private StatusTopico status;
    private List<RespostaDTO> respostas;
}
