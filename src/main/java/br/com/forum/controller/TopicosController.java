package br.com.forum.controller;

import br.com.forum.controller.dto.TopicoDTO;
import br.com.forum.modelo.Curso;
import br.com.forum.modelo.Topico;
import br.com.forum.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TopicosController {

    @Autowired
    private TopicoService topicoService;

    @RequestMapping("/topicos")
    @ResponseBody
    public List<TopicoDTO> lista(){
        Topico topico = new Topico("Duvida", "abc", new Curso("Spring", "Back-end"));
        List<Topico> list = Arrays.asList(topico, topico, topico, topico);
        List<TopicoDTO> dtoList = list.parallelStream().map(t -> topicoService.fromDTO(t)).collect(Collectors.toList());
        return dtoList;
    }
}
