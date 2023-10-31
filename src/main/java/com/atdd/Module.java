package com.atdd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.atdd.curso.CursoRepositorio;
import com.atdd.usuario.UsuarioRepositorio;

@Configuration
public class Module {

      @Bean
    public CursoRepositorio createCursoRepositorio() {
        return new CursoRepositorio();
    }

    @Bean
    public UsuarioRepositorio createUsuarioRepositorio() {
        return new UsuarioRepositorio();
    }

}