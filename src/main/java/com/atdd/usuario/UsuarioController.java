package com.atdd.usuario;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UsuarioController {

    private List<Usuario> usuarios = Arrays.asList(
        new Usuario("Usuario 1", 1),
        new Usuario("Usuario 2", 2),
        new Usuario("Usuario 3", 3),
        new Usuario("Usuario 4", 4),
        new Usuario("Usuario 5", 5)
    );

    @GetMapping("/usuarios")
    List<Usuario> all() {
        return usuarios;
    }

}
