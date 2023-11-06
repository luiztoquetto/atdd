package com.atdd.usuario.infra.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atdd.usuario.dominio.entidades.Usuario;
import com.atdd.usuario.dominio.repositorios.UsuarioRepositorio;

public interface UsuarioRepositorioJPA extends JpaRepository<Usuario, Long>, UsuarioRepositorio {
    default Usuario getUsuarioPorId(long id) {
        Optional<Usuario> usuario = this.findById(id);

        if (usuario.isEmpty()) {
            return null;
        }

        return usuario.get();
    }

    default List<Usuario> getUsuarios() {
        return this.findAll();
    }

    default void salvarUsuario(Usuario usuario) {
        this.save(usuario);
    }

    default void salvarUsuario(List<Usuario> usuarios) {
        this.saveAll(usuarios);
    }
}
