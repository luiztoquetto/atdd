package com.atdd;

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

import com.atdd.comentario.dominio.entidades.Comentario;
import com.atdd.comentario.dominio.repositorios.ComentarioRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ComentarioControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;

    // Luiz Fernando - 200359
    @Test
    public void deveRetornarUmComentarioDTO() throws Exception {
        Comentario comentario = new Comentario();
        
        comentario.setId(1);
        comentario.setMensagem("Teste mensagem");

        comentarioRepositorio.salvarComentario(comentario);

        mockMvc
            .perform(MockMvcRequestBuilders.get("/comentarios/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem").value("Teste mensagem"));
    }

}
