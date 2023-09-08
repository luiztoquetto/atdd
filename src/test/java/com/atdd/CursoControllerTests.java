package com.atdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.atdd.curso.Curso;
import com.atdd.curso.CursoController;
import com.atdd.curso.CursoRepositorio;
import com.atdd.usuario.Usuario;
import com.atdd.usuario.UsuarioRepositorio;

public class CursoControllerTests {
    @Mock
    private UsuarioRepositorio usuarioRepositorio;

    @Mock
    private CursoRepositorio cursoRepositorio;

    private CursoController cursoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cursoController = new CursoController(usuarioRepositorio, cursoRepositorio);
    }

    @Test
    void solicitacaoDeMatriculaDeveSerAceita() {
        int usuarioId = 1;
        int cursoId = 2;

        Usuario usuario = new Usuario("Usuario 1", usuarioId, Arrays.asList(), 1);

        when(usuarioRepositorio.getUsuarioPorId(usuarioId)).thenReturn(usuario);

        cursoController.realizarMatricula(usuarioId, cursoId);

        assertEquals(1, usuario.getMatriculas().size());
    }
}
