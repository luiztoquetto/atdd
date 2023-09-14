package com.atdd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.atdd.usuario.UsuarioRepositorio;

@Configuration
public class Module {

    @Bean
    public UsuarioRepositorio createUsuarioRepositorio() {
        return new UsuarioRepositorio();
    }

}