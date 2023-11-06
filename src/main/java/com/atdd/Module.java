package com.atdd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.atdd.curso.dominio.repositorios.CursoRepositorio;
import com.atdd.curso.infra.CursoRepositorioInMemory;
import com.atdd.usuario.infra.UsuarioRepositorioInMemory;

@Configuration
public class Module {

    @Bean
    public CursoRepositorio createCursoRepositorio() {
        return new CursoRepositorioInMemory();
    }

    @Bean
    public UsuarioRepositorioInMemory createUsuarioRepositorio() {
        return new UsuarioRepositorioInMemory();
    }

}