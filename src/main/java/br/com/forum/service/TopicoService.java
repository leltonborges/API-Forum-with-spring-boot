package br.com.forum.service;

import br.com.forum.controller.dto.TopicoDTO;
import br.com.forum.modelo.Topico;
import br.com.forum.repository.TopicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicoService {
    private ModelMapper mapper;
    private TopicoRepository topicoRepository;

    @Autowired
    public TopicoService(ModelMapper mapper, TopicoRepository topicoRepository) {
        this.mapper = mapper;
        this.topicoRepository = topicoRepository;
    }

    public List<TopicoDTO> findAll() {
        return topicoRepository.findAll()
                .parallelStream()
                .map(this::fromDTO)
                .collect(Collectors.toList());
    }

    public <S extends Topico> S save(S entity) {
        return topicoRepository.save(entity);
    }

    public void deleteById(Long aLong) {
        topicoRepository.deleteById(aLong);
    }

    public void delete(Topico entity) {
        topicoRepository.delete(entity);
    }

    public List<TopicoDTO> findByCursoNome(String nameCurso) {
        return topicoRepository
                .findByCurso_Nome(nameCurso)
                .parallelStream()
                .map(this::fromDTO)
                .collect(Collectors.toList());
    }

    public TopicoDTO fromDTO(Topico topico){
        return mapper.map(topico, TopicoDTO.class);
    }

}
