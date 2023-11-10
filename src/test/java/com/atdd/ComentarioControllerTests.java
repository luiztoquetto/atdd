package com.atdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.atdd.aula.dominio.repositorios.AulaRepositorio;
import com.atdd.comentario.dominio.entidades.Comentario;
import com.atdd.comentario.dominio.repositorios.ComentarioRepositorio;
import com.atdd.comentario.presenter.controllers.ComentarioController;
import com.atdd.comentario.presenter.dtos.outputs.ComentarioOutputDto;
import com.atdd.usuario.dominio.repositorios.UsuarioRepositorio;

public class ComentarioControllerTests {

    @Mock
    private UsuarioRepositorio usuarioRepositorio;

    @Mock
    private AulaRepositorio aulaRepositorio;

    @Mock
    private ComentarioRepositorio comentarioRepositorio;

    private ComentarioController comentarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        comentarioController = new ComentarioController(
            comentarioRepositorio,
            usuarioRepositorio, 
            aulaRepositorio
        );
    }

    // Luiz Fernando - 200359
    @Test
    void deveRetornarUmComentarioDTO() {
        Comentario comentario = new Comentario();

        comentario.setId(1);
        comentario.setMensagem("Teste mensagem");

        when(comentarioRepositorio.getComentarioPorId(anyLong())).thenReturn(comentario);

        ComentarioOutputDto saida = comentarioController.getComentarioPorId(1);

        assertEquals(saida.getId(), 1);
        assertEquals(saida.getMensagem(), "Teste mensagem");
    }

    // Luiz Fernando - 200359
    @Test
    void deveRetornarErroCasoNaoExitaComentarioDTO() {
        when(comentarioRepositorio.getComentarioPorId(anyLong())).thenReturn(null);

        ResponseStatusException exception = assertThrows(
            ResponseStatusException.class,
            () -> comentarioController.getComentarioPorId(1)
        );

        assertEquals(exception.getStatusCode(), HttpStatus.NOT_FOUND);
    }

}
