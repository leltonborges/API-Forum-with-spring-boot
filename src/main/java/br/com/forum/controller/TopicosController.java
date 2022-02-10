package br.com.forum.controller;

import br.com.forum.modelo.Curso;
import br.com.forum.modelo.Topico;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class TopicosController {

    @RequestMapping("/topicos")
    @ResponseBody
    public List<Topico> lista(){
        Topico topico = new Topico("Duvida", "abc", new Curso("Spring", "Back-end"));
        return Arrays.asList(topico, topico, topico, topico);
    }
}
