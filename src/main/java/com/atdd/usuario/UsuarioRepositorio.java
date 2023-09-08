package com.atdd.usuario;

import java.util.Arrays;
import java.util.List;

import com.atdd.matricula.Matricula;

public class UsuarioRepositorio {

    private List<Usuario> usuarios = Arrays.asList(
            new Usuario(
                    "Usuario 1",
                    1,
                    Arrays.asList(
                            new Matricula(10.0, 6.0, 10.0, 1),
                            new Matricula(9.0, 7.0, 9.0, 2),
                            new Matricula(8.0, 8.0, 9.0, 3),
                            new Matricula(7.0, 9.0, 5.0, 4),
                            new Matricula(6.0, 10.0, 7.0, 5)),
                    1),
            new Usuario("Usuario 2", 2, Arrays.asList(),0),
            new Usuario("Usuario 3", 3, Arrays.asList(),0),
            new Usuario("Usuario 4", 4, Arrays.asList(),0),
            new Usuario("Usuario 5", 5, Arrays.asList(), 0));

    public Usuario getUsuarioPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }
}
