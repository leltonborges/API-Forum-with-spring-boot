package br.com.forum.service;

import br.com.forum.controller.dto.TopicoDTO;
import br.com.forum.modelo.Topico;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    @Autowired
    private ModelMapper mapper;

    public TopicoDTO fromDTO(Topico topico){
        return mapper.map(topico, TopicoDTO.class);
    }

}
