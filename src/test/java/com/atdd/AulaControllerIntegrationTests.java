package com.atdd;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.atdd.aula.dominio.entidades.Aula;
import com.atdd.aula.dominio.repositorios.AulaRepositorio;
import com.atdd.curso.dominio.entidades.Curso;
import com.atdd.curso.dominio.repositorios.CursoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AulaControllerIntegrationTests {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AulaRepositorio aulaRepositorio;

    @Autowired
    private CursoRepositorio cursoRepositorio;

    // Vinícius Martins Granso - 224072
    @Test
    public void deveRetornarAsAulasCadastradas() throws Exception {
        Curso curso = new Curso("Curso 1");
        cursoRepositorio.salvarCurso(curso);

        List<Aula> aulas = Arrays.asList(
            new Aula("Aula 1", curso),
            new Aula("Aula 2", curso),
            new Aula("Aula 3", curso),
            new Aula("Aula 4", curso),
            new Aula("Aula 5", curso)
        );

        aulaRepositorio.salvarAula(aulas);

        mockMvc
        .perform(MockMvcRequestBuilders.get("/aulas"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Aula 1"));
    }

}
