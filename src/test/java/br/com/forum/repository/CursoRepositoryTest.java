package br.com.forum.repository;

import br.com.forum.exception.curso.NotFoundCursoException;
import br.com.forum.modelo.Curso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// Não substituir as configurações para usar seu banco, e não H2
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
//@SpringBootTest
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void deveriaCarregarUmCursoAoBuscarPeloSeuNome() {
        String nomeCurso = "HTML 5";
        Optional<Curso> cursoOptional = cursoRepository.findByNome(nomeCurso);
        assertTrue(cursoOptional.isPresent());
        assertEquals(nomeCurso, cursoOptional.get().getNome());
    }

    @Test
    void naoDeveriaCarregarUmCursoInexistente() {
        String nomeCurso = "JPA";
        Optional<Curso> cursoOptional = cursoRepository.findByNome(nomeCurso);
        assertFalse(cursoOptional.isPresent());
        assertThrows(NotFoundCursoException.class, () -> cursoOptional.orElseThrow(() -> new NotFoundCursoException("não encontrado")));
    }
}