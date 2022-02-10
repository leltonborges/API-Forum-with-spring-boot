package br.com.forum.service;

import br.com.forum.modelo.Curso;
import br.com.forum.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursoService {

    private CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Optional<Curso> findByNome(String nameCursos) {
        return cursoRepository.findByNome(nameCursos);
    }
}
