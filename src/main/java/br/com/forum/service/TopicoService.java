package br.com.forum.service;

import br.com.forum.dto.topico.DetalhesTopico;
import br.com.forum.dto.topico.NewTopicoDTO;
import br.com.forum.dto.topico.TopicoAtualizar;
import br.com.forum.dto.topico.TopicoDTO;
import br.com.forum.exception.curso.NotFoundCursoException;
import br.com.forum.exception.topico.NotfoundTopicoException;
import br.com.forum.modelo.Curso;
import br.com.forum.modelo.Topico;
import br.com.forum.repository.CursoRepository;
import br.com.forum.repository.TopicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Topico findById(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new NotfoundTopicoException("Topico não encontrado, id: " + id));

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

    public void deleteById(Long id) {
        topicoRepository.deleteById(id);
    }

    public Page<Topico> findAll(Pageable pageable) {
        return topicoRepository.findAll(pageable);
    }

    public Page<Topico> findByCursoNome(String nameCurso, Pageable pageable) {
        return topicoRepository
                .findByCurso_Nome(nameCurso, pageable);
    }

    public TopicoDTO fromDTO(Topico topico) {
        return mapper.map(topico, TopicoDTO.class);
    }

    public Topico from(NewTopicoDTO newTopicoDTO) {
        Curso cursoOptional = this.cursoRepository
                .findByNome(newTopicoDTO.getCurso())
                .orElseThrow(() -> new NotFoundCursoException("Erro ao encontrar o curso: " + newTopicoDTO.getCurso()));

        return this.mapper.typeMap(NewTopicoDTO.class, Topico.class)
                .addMapping(src -> cursoOptional, (dest, v) -> dest.setCurso(cursoOptional))
                .map(newTopicoDTO);
    }

    public DetalhesTopico fromDetalhesTopico(Topico topico) {
        return this.mapper.map(topico, DetalhesTopico.class);
    }

    public void from(TopicoAtualizar topicoAtualizar, Topico topico) {
        this.mapper.map(topicoAtualizar, topico);
    }
}
