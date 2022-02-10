package br.com.forum.controller;

import br.com.forum.dto.topico.NewTopicoDTO;
import br.com.forum.dto.topico.TopicoDTO;
import br.com.forum.modelo.Topico;
import br.com.forum.service.CursoService;
import br.com.forum.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("topicos")
public class TopicosController {

    @Autowired
    private TopicoService topicoService;
    @Autowired
    private CursoService cursoService;

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public List<TopicoDTO> lista(
            @RequestParam(value = "nomeCurso", defaultValue = "") String nomeCurso
    ){
        if (nomeCurso.isBlank())
            return this.topicoService.findAll();
        else {
            return this.topicoService.findByCursoNome(nomeCurso);
        }
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody NewTopicoDTO newTopicoDTO, UriComponentsBuilder uriBuilder){
        Topico topico = topicoService.from(newTopicoDTO);
        topicoService.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(topicoService.fromDTO(topico));
    }
}
