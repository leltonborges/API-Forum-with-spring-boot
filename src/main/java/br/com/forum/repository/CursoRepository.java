package br.com.forum.repository;

import br.com.forum.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNome(String nameCursos);
}
