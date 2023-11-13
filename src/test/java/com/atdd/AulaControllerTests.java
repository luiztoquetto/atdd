package com.atdd;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
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
import com.atdd.usuario.dominio.repositorios.UsuarioRepositorio;

public class AulaControllerTests {
    @Mock
    private UsuarioRepositorio usuarioRepositorio;

    @Mock
    private CursoRepositorio cursoRepositorio;

    private AulaController aulaController;

    private AulaRepositorio aulaRepositorio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        aulaController = new AulaController(
        aulaRepositorio, cursoRepositorio
        );
    }

    @Test
    void deveRetornarSeExistemAulasDTO() {
        Curso curso = new Curso();
        curso.setName("Curso 1");
        curso.setId(1);

        ArrayList<Aula> minhaLista = new ArrayList<Aula>();

        Aula aula = new Aula();
        aula.setCurso(curso);
        aula.setId(1);
        aula.setName("Aula 1");
        minhaLista.add(aula);


        when(aulaRepositorio.getAulas()).thenReturn(minhaLista);

        List<AulaOutputDto> saida = aulaController.getAulas();

        assertEquals(saida.size(), 0);
    }

}
