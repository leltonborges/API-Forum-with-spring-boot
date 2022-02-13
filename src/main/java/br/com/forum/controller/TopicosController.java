package br.com.forum.controller;

import br.com.forum.dto.topico.DetalhesTopico;
import br.com.forum.dto.topico.NewTopicoDTO;
import br.com.forum.dto.topico.TopicoAtualizar;
import br.com.forum.dto.topico.TopicoDTO;
import br.com.forum.modelo.Topico;
import br.com.forum.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("topicos")
public class TopicosController {

    @Autowired
    private TopicoService topicoService;

//    @Cacheable(cacheNames = "Topicos", key = "#root.methodName")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<TopicoDTO> lista(
            @RequestParam(value = "nomeCurso", defaultValue = "") String nomeCurso,
            @PageableDefault(size = 2, sort = "id") Pageable pageable
    ){

        if (nomeCurso.isBlank()) {
            return this.topicoService.findAll(pageable).map(this.topicoService::fromDTO);
        }
        else {
            return this.topicoService.findByCursoNome(nomeCurso, pageable).map(this.topicoService::fromDTO);
        }
    }

    @CacheEvict(cacheNames = "Topicos", allEntries = true)
    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid NewTopicoDTO newTopicoDTO, UriComponentsBuilder uriBuilder){
        Topico topico = topicoService.from(newTopicoDTO);
        topicoService.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(topicoService.fromDTO(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesTopico> detalhar(@PathVariable Long id){
        Topico topico = this.topicoService.findById(id);
        return ResponseEntity.ok(this.topicoService.fromDetalhesTopico(topico));
    }

    @PutMapping("/{id}")
    @CacheEvict(cacheNames = "Topicos", allEntries = true)
    @Transactional
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @Valid@ RequestBody TopicoAtualizar topicoAtualizar){
        Topico topico = this.topicoService.findById(id);
        this.topicoService.from(topicoAtualizar, topico);
        this.topicoService.save(topico);
        return ResponseEntity.ok(this.topicoService.fromDTO(topico));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(cacheNames = "Topicos", allEntries = true)
    @CachePut(cacheNames = "Topicos")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Long id){
        this.topicoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
