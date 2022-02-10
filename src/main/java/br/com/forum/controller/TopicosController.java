package br.com.forum.controller;

import br.com.forum.controller.dto.TopicoDTO;
import br.com.forum.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicosController {

    @Autowired
    private TopicoService topicoService;

    @RequestMapping("/topicos")
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
}
