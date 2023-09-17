package com.atdd;

import com.atdd.curso.CursoController;
import com.atdd.matricula.Matricula;
import com.atdd.usuario.Usuario;
import com.atdd.usuario.UsuarioRepositorio;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CursoControllerTests {
    @Mock
    private UsuarioRepositorio usuarioRepositorio;

    private CursoController cursoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cursoController = new CursoController(usuarioRepositorio);
    }

    // Leonardo Dimarchi - 200109
    @Test
    void solicitacaoDeMatriculaDeveSerAceita() {
        int usuarioId = 1;
        int cursoId = 2;

        Usuario usuario = new Usuario("Usuario 1", usuarioId, Arrays.asList(), 1);

        when(usuarioRepositorio.getUsuarioPorId(usuarioId)).thenReturn(usuario);

        cursoController.realizarMatricula(usuarioId, cursoId);

        assertEquals(1, usuario.getMatriculas().size());
    }

    // Leonardo Dimarchi - 200109
    @Test
    void deveRemoverUmPontoAposMatricular() {
        int usuarioId = 1;
        int cursoId = 2;

        Usuario usuario = new Usuario("Usuario 1", usuarioId, Arrays.asList(), 1);

        when(usuarioRepositorio.getUsuarioPorId(usuarioId)).thenReturn(usuario);

        cursoController.realizarMatricula(usuarioId, cursoId);

        assertEquals(0, usuario.getQuantidadeDeMatriculasDisponiveis());
    }

    // Luiz Fernando - 200359
    @Test
    void ganhaMatriculaSeMediaMaiorQueSete() {
        int usuarioId = 2;
        int cursoId = 2;

        Usuario usuario = new Usuario(
                "Usuario 2",
                usuarioId,
                Arrays.asList(
                        new Matricula(10.0, 6.0, 10.0, 2)),
                1);

        when(usuarioRepositorio.getUsuarioPorId(usuarioId)).thenReturn(usuario);

        cursoController.finalizarCurso(usuarioId, cursoId);

        assertEquals(4, usuario.getQuantidadeDeMatriculasDisponiveis());
    }
 // Laura Cecilia - 200343
    @Test
    void naoAcessaCursoSeNaoMatriculado() {
        int usuarioId = 3; // ID de um usuário não matriculado
        int cursoId = 4;   // ID de um curso

        // usuário que não está matriculado no curso 4
        Usuario usuarioNaoMatriculado = new Usuario("Usuario 3", usuarioId, Arrays.asList(), 3);

        // busca pelo usuário no repositório retornando o usuário não matriculado
        when(usuarioRepositorio.getUsuarioPorId(usuarioId)).thenReturn(usuarioNaoMatriculado);
        
        assertThrows(ResponseStatusException.class,()->{
        	cursoController.getCurso(cursoId,usuarioNaoMatriculado);
});
    }
    
}















