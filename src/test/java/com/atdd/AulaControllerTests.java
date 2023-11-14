package com.atdd;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.atdd.aula.dominio.entidades.Aula;
import com.atdd.aula.dominio.repositorios.AulaRepositorio;
import com.atdd.aula.presenter.controllers.AulaController;
import com.atdd.aula.presenter.dtos.outputs.AulaOutputDto;
import com.atdd.curso.dominio.entidades.Curso;
import com.atdd.curso.dominio.repositorios.CursoRepositorio;

public class AulaControllerTests {
    @Mock
    private CursoRepositorio cursoRepositorio;

    private AulaController aulaController;

    @Mock
    private AulaRepositorio aulaRepositorio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        aulaController = new AulaController(
        aulaRepositorio, cursoRepositorio
        );
    }

    // Vinícius Martins Granso - 224072
    @Test
    void deveRetornarSeExistemAulasDTO() {
        Curso curso = new Curso("Curso 1");
        List<Aula> listaDeAulas = Arrays.asList(
            new Aula("Aula 1", curso), new Aula("Aula 2", curso));

        when(aulaRepositorio.getAulas()).thenReturn(listaDeAulas);

        List<AulaOutputDto> saida = aulaController.getAulas();

        assertEquals(2, saida.size());
        assertEquals("Aula 1", saida.get(0).getName());
        assertEquals("Aula 2", saida.get(1).getName());
    }

}
