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

import com.atdd.usuario.dominio.entidades.Usuario;
import com.atdd.usuario.dominio.repositorios.UsuarioRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//Laura Cecilia - 200343
public class UsuarioControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Test
    public void deveRetornarOsUsuariosCadastrados() throws Exception {
        List<Usuario> usuarios = Arrays.asList(
        		new Usuario("Usuario 1", 1, Arrays.asList()),
                new Usuario("Usuario 2", 2, Arrays.asList()),
                new Usuario("Usuario 3", 1, Arrays.asList()),
                new Usuario("Usuario 4", 2, Arrays.asList()),
                new Usuario("Usuario 5", 1, Arrays.asList())
                );

        usuarioRepositorio.salvarUsuario(usuarios);

        mockMvc
                .perform(MockMvcRequestBuilders.get("/usuarios"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Usuario 1"));
    }
	
}
