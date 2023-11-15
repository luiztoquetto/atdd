package com.atdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.atdd.aula.dominio.entidades.Aula;
import com.atdd.aula.dominio.repositorios.AulaRepositorio;
import com.atdd.comentario.dominio.entidades.Comentario;
import com.atdd.comentario.dominio.repositorios.ComentarioRepositorio;
import com.atdd.comentario.presenter.controllers.ComentarioController;
import com.atdd.comentario.presenter.dtos.inputs.ComentarioInputDto;
import com.atdd.comentario.presenter.dtos.outputs.ComentarioOutputDto;
import com.atdd.usuario.dominio.entidades.Usuario;
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
                aulaRepositorio);
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

    // Luiz Fernando - 200359
    @Test
    void deveRetornarErroCasoNaoExistaAula() {
        ComentarioInputDto entrada = new ComentarioInputDto();

        entrada.setMensagem("Teste mensagem");
        entrada.setAulaId(1L);
        entrada.setUsuarioId(1L);

        when(aulaRepositorio.getAulaPorId(anyLong())).thenReturn(null);

        ResponseStatusException exception = assertThrows(
            ResponseStatusException.class,
            () -> comentarioController.postComentario(entrada)
        );

        assertEquals(exception.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    // Luiz Fernando - 200359
    @Test
    void deveRetornarErroCasoNaoExistaUsuario() {
        ComentarioInputDto entrada = new ComentarioInputDto();

        entrada.setMensagem("Teste mensagem");
        entrada.setAulaId(1L);
        entrada.setUsuarioId(1L);

        when(usuarioRepositorio.getUsuarioPorId(anyLong())).thenReturn(null);

        ResponseStatusException exception = assertThrows(
            ResponseStatusException.class,
            () -> comentarioController.postComentario(entrada)
        );

        assertEquals(exception.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    // Leonardo Dimarchi - 200109
    @Test
    void deveCriarERetornarComentarioDTO() {
        ComentarioInputDto entrada = new ComentarioInputDto();

        entrada.setUsuarioId(1L);
        entrada.setAulaId(1L);
        entrada.setMensagem("Teste mensagem");

        Usuario usuario = new Usuario();

        usuario.setId(1L);

        Aula aula = new Aula();

        aula.setId(1L);

        when(usuarioRepositorio.getUsuarioPorId(anyLong())).thenReturn(usuario);
        when(aulaRepositorio.getAulaPorId(anyLong())).thenReturn(aula);

        Comentario comentarioSalvo = new Comentario();

        comentarioSalvo.setId(10);
        comentarioSalvo.setMensagem("Teste mensagem");

        when(comentarioRepositorio.salvarComentario(any(Comentario.class))).thenReturn(comentarioSalvo);

        ComentarioOutputDto resposta = comentarioController.postComentario(entrada);

        assertInstanceOf(ComentarioOutputDto.class, resposta);

        assertEquals("Teste mensagem", resposta.getMensagem());
        assertEquals(10, resposta.getId());
    }

}
