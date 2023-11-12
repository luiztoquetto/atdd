package com.atdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import com.atdd.curso.dominio.entidades.Curso;
import com.atdd.curso.infra.repositorios.CursoRepositorioInMemory;
import com.atdd.curso.presenter.controllers.CursoController;
import com.atdd.curso.presenter.dtos.outputs.CursoOutputDto;
import com.atdd.usuario.dominio.entidades.Matricula;
import com.atdd.usuario.dominio.entidades.Usuario;
import com.atdd.usuario.infra.repositorios.UsuarioRepositorioInMemory;
import com.atdd.usuario.presenter.controllers.UsuarioController;
import com.atdd.usuario.presenter.dtos.outputs.UsuarioOutputDto;


public class CursoControllerTests {
    @Mock
    private UsuarioRepositorioInMemory usuarioRepositorio;

    @Mock
    private CursoRepositorioInMemory cursoRepositorio;

    private CursoController cursoController;
    
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cursoController = new CursoController(usuarioRepositorio, cursoRepositorio);
    }
    // Laura Cecilia - 200343
    @Test
    void devePuxarTodosOsUsuariosComDTO() {
        List<Usuario> usuariosRetornados = Arrays.asList(
        	new Usuario(
        			"Usuario 1",1,Arrays.asList()),
        	new Usuario(
                    "Usuario 2",2,Arrays.asList()));

        when(usuarioRepositorio.getUsuarios()).thenReturn(usuariosRetornados);

        List<UsuarioOutputDto> resposta = usuarioController.getUsuarios();

        assertEquals(2, resposta.size());
        assertEquals("Usuario 1", resposta.get(0).getName());
        assertEquals("Usuario 2", resposta.get(1).getName());
    }
    
    // Leonardo Dimarchi - 200109
    @Test
    void devePuxarTodosOsCursosComDTO() {
        List<Curso> cursosRetornados = Arrays.asList(
                new Curso("Curso 1"), new Curso("Curso 2"));

        when(cursoRepositorio.getCursos()).thenReturn(cursosRetornados);

        List<CursoOutputDto> resposta = cursoController.getCursos();

        assertEquals(2, resposta.size());
        assertEquals("Curso 1", resposta.get(0).getName());
        assertEquals("Curso 2", resposta.get(1).getName());
    }

    // Leonardo Dimarchi - 200109
    @Test
    void solicitacaoDeMatriculaDeveSerAceita() {
        int usuarioId = 1;
        int cursoId = 2;

        Usuario usuario = new Usuario("Usuario 1", 1, Arrays.asList());
        usuario.setId(usuarioId);

        when(usuarioRepositorio.getUsuarioPorId(usuarioId)).thenReturn(usuario);

        cursoController.realizarMatricula(usuarioId, cursoId);

        assertEquals(1, usuario.getMatriculas().size());
    }

    // Leonardo Dimarchi - 200109
    @Test
    void deveRemoverUmPontoAposMatricular() {
        int usuarioId = 1;
        int cursoId = 2;

        Usuario usuario = new Usuario("Usuario 1", 1, Arrays.asList());
        usuario.setId(usuarioId);

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
                1,
                Arrays.asList(
                        new Matricula(10.0, 6.0, 10.0, 2)));
        usuario.setId(usuarioId);

        when(usuarioRepositorio.getUsuarioPorId(usuarioId)).thenReturn(usuario);

        cursoController.finalizarCurso(usuarioId, cursoId);

        assertEquals(4, usuario.getQuantidadeDeMatriculasDisponiveis());
    }

    // Laura Cecilia - 200343
    @Test
    void naoAcessaCursoSeNaoMatriculado() {
        int usuarioId = 3; // ID de um usuário não matriculado
        int cursoId = 4; // ID de um curso

        // usuário que não está matriculado no curso 4
        Usuario usuarioNaoMatriculado = new Usuario("Usuario 3", 3, Arrays.asList());
        usuarioNaoMatriculado.setId(usuarioId);

        assertThrows(ResponseStatusException.class, () -> {
            cursoController.getCurso(cursoId, usuarioNaoMatriculado);
        });
    }

    // Vinicius Martins - 224072
    @Test
    void naoRecebeMatriculaSeMediaMenorQueSete() {
        int usuarioId = 2;
        int cursoId = 2;

        Usuario usuario = new Usuario(
                "Usuario 2",
                1,
                Arrays.asList(
                        new Matricula(1.0, 3.0, 10.0, 2)));
        usuario.setId(usuarioId);

        when(usuarioRepositorio.getUsuarioPorId(usuarioId)).thenReturn(usuario);

        cursoController.finalizarCurso(usuarioId, cursoId);

        assertEquals(1, usuario.getQuantidadeDeMatriculasDisponiveis());
    }

}
