package com.atdd.usuario;
import com.atdd.matricula.Matricula;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

@RestController
class UsuarioController {

    private List<Usuario> usuarios = Arrays.asList(
        new Usuario(
            "Usuario 1",
            1,
            Arrays.asList(
                new Matricula(10.0, 6.0, 10.0, 1 , 1),
                new Matricula(9.0, 7.0, 9.0, 2 , 2),
                new Matricula(8.0, 8.0, 9.0, 3 , 3),
                new Matricula(7.0, 9.0, 5.0, 4 , 4),
                new Matricula(6.0, 10.0, 7.0, 5 , 5)
            )
        ),
        new Usuario("Usuario 2", 2, Arrays.asList()),
        new Usuario("Usuario 3", 3, Arrays.asList()),
        new Usuario("Usuario 4", 4, Arrays.asList()),
        new Usuario("Usuario 5", 5, Arrays.asList())
    );

}
