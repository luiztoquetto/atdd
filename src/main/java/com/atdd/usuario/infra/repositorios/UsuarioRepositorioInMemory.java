package com.atdd.usuario.infra.repositorios;

import java.util.Arrays;
import java.util.List;

import com.atdd.usuario.dominio.entidades.Matricula;
import com.atdd.usuario.dominio.entidades.Usuario;
import com.atdd.usuario.dominio.repositorios.UsuarioRepositorio;

public class UsuarioRepositorioInMemory implements UsuarioRepositorio {

    private List<Usuario> usuarios = Arrays.asList(
            new Usuario(
                    "Usuario 1",
                    1,
                    Arrays.asList(
                            new Matricula(10.0, 6.0, 10.0, 1),
                            new Matricula(9.0, 7.0, 9.0, 2),
                            new Matricula(8.0, 8.0, 9.0, 3),
                            new Matricula(7.0, 9.0, 5.0, 4),
                            new Matricula(6.0, 10.0, 7.0, 5))),
            new Usuario("Usuario 2", 0, Arrays.asList()),
            new Usuario("Usuario 3", 0, Arrays.asList()),
            new Usuario("Usuario 4", 0, Arrays.asList()),
            new Usuario("Usuario 5", 0, Arrays.asList()));

    private long nextId = 1;

    public Usuario getUsuarioPorId(long id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario salvarUsuario(Usuario usuario) {
        usuario.setId(nextId);
        usuarios.add(usuario);
        nextId++;
        return usuario;
    }

    public List<Usuario> salvarUsuario(List<Usuario> usuariosParaSalvar) {
        for (Usuario usuario : usuariosParaSalvar) {
            usuario.setId(nextId);
            usuarios.add(usuario);
            nextId++;
        }
        return usuariosParaSalvar;
    }
}
