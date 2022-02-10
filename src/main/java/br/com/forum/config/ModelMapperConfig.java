package br.com.forum.config;

import br.com.forum.dto.resposta.RespostaDTO;
import br.com.forum.dto.topico.DetalhesTopico;
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

        Provider<List<Resposta>> providerRespostas = request -> new ArrayList<>();

        mapper.typeMap(Topico.class, DetalhesTopico.class)
                .addMapping(src -> src.getAutor().getNome(), DetalhesTopico::setNomeAutor)
                .addMappings(mapping -> mapping.<List<RespostaDTO>>map(src -> src.getRespostas(), DetalhesTopico::setRespostas));

        return mapper;
    }
}
