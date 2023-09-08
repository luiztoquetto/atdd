package com.atdd;

import com.atdd.curso.CursoController;
import com.atdd.usuario.Usuario;
import com.atdd.usuario.UsuarioRepositorio;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class CursoControllerTests {
    @Mock
    private UsuarioRepositorio usuarioRepositorio;

    private CursoController cursoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cursoController = new CursoController(usuarioRepositorio);
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
