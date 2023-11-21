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

import com.atdd.curso.dominio.entidades.Curso;
import com.atdd.curso.dominio.repositorios.CursoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CursoControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CursoRepositorio cursoRepositorio;

    // Leonardo Dimarchi - 200109
    @Test
    public void deveRetornarOsCursosCadastrados() throws Exception {
        List<Curso> cursos = Arrays.asList(
                new Curso("Curso 1"),
                new Curso("Curso 2"),
                new Curso("Curso 3"),
                new Curso("Curso 4"),
                new Curso("Curso 5"));

        cursoRepositorio.salvarCurso(cursos);

        mockMvc
                .perform(MockMvcRequestBuilders.get("/cursos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Curso 1"));
    }
}
