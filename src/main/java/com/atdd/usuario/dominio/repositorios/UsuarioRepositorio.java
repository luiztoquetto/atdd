package com.atdd.usuario.dominio.repositorios;

import java.util.List;

import com.atdd.usuario.dominio.entidades.Usuario;

public interface UsuarioRepositorio {
    Usuario getUsuarioPorId(long id);

    List<Usuario> getUsuarios();

    void salvarUsuario(Usuario usuario);
}
