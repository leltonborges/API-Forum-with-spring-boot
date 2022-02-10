package br.com.forum.service;

import br.com.forum.dto.topico.NewTopicoDTO;
import br.com.forum.dto.topico.TopicoDTO;
import br.com.forum.modelo.Curso;
import br.com.forum.modelo.Topico;
import br.com.forum.repository.CursoRepository;
import br.com.forum.repository.TopicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicoService {
    private ModelMapper mapper;
    private TopicoRepository topicoRepository;
    private CursoRepository cursoRepository;

    @Autowired
    public TopicoService(ModelMapper mapper, TopicoRepository topicoRepository, CursoRepository cursoRepository) {
        this.mapper = mapper;
        this.topicoRepository = topicoRepository;
        this.cursoRepository = cursoRepository;
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

    public Topico from(NewTopicoDTO newTopicoDTO){
        Curso cursoOptional = this.cursoRepository
                .findByNome(newTopicoDTO.getCurso())
                .orElseThrow(() -> new RuntimeException("Erro ao encontrar o curso: " + newTopicoDTO.getCurso()));

        return this.mapper.typeMap(NewTopicoDTO.class, Topico.class)
                .addMapping(src -> cursoOptional, (dest, v) -> dest.setCurso(cursoOptional))
                .map(newTopicoDTO);
    }

}
