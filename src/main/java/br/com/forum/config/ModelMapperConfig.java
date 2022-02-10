package br.com.forum.config;

import br.com.forum.dto.resposta.RespostaDTO;
import br.com.forum.dto.topico.DetalhesTopico;
import br.com.forum.dto.topico.TopicoAtualizar;
import br.com.forum.modelo.Resposta;
import br.com.forum.modelo.Topico;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper mapper(){
        ModelMapper mapper = new ModelMapper();

        mapper.typeMap(Resposta.class, RespostaDTO.class)
                .addMapping(src -> src.getAutor().getNome(), RespostaDTO::setNomeAutor);
        mapper.typeMap(Topico.class, DetalhesTopico.class);
        mapper.typeMap(TopicoAtualizar.class, Topico.class);

        return mapper;
    }
}
