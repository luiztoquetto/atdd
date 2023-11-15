package com.atdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.atdd.aula.dominio.entidades.Aula;
import com.atdd.aula.presenter.dtos.outputs.AulaOutputDto;
import com.atdd.usuario.dominio.entidades.Usuario;
import com.atdd.usuario.infra.repositorios.UsuarioRepositorioInMemory;
import com.atdd.usuario.presenter.controllers.UsuarioController;
import com.atdd.usuario.presenter.dtos.outputs.UsuarioOutputDto;

public class UsuarioControllerTests {
    @Mock
    private UsuarioRepositorioInMemory usuarioRepositorio;

    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioController = new UsuarioController(usuarioRepositorio);
    }

    // Luiz Fernando - 200359
    @Test
    void deveRetornarVazioSeNaoTerUsuariosCadastrados() {
        List<Usuario> listaVazia = Arrays.asList();

        when(usuarioRepositorio.getUsuarios()).thenReturn(listaVazia);

        List<UsuarioOutputDto> resposta = usuarioController.getUsuarios();

        assertEquals(0, resposta.size());
    }

    // Laura Cecilia - 200343
    @Test
    void devePuxarTodosOsUsuariosComDTO() {
        List<Usuario> usuariosRetornados = Arrays.asList(
                new Usuario(
                        "Usuario 1", 1, Arrays.asList()),
                new Usuario(
                        "Usuario 2", 2, Arrays.asList()));

        when(usuarioRepositorio.getUsuarios()).thenReturn(usuariosRetornados);

        List<UsuarioOutputDto> resposta = usuarioController.getUsuarios();

        assertEquals(2, resposta.size());
        assertEquals("Usuario 1", resposta.get(0).getName());
        assertEquals("Usuario 2", resposta.get(1).getName());
    }

}
