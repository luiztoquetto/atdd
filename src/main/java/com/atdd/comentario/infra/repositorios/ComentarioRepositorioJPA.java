package com.atdd.comentario.infra.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.atdd.comentario.dominio.entidades.Comentario;
import com.atdd.comentario.dominio.repositorios.ComentarioRepositorio;

public interface ComentarioRepositorioJPA extends JpaRepository<Comentario, Long>, ComentarioRepositorio {
    default Comentario getComentarioPorId(long id) {
        Optional<Comentario> Comentario = this.findById(id);

        if (Comentario.isEmpty()) {
            return null;
        }

        return Comentario.get();
    }

    @Query("SELECT c FROM Comentario c WHERE c.aula.id = :aulaId")
    List<Comentario> getComentariosPorAulaId(@Param("aulaId") long aulaId);

    default void salvarComentario(Comentario Comentario) {
        this.save(Comentario);
    }

    default void salvarComentario(List<Comentario> Comentarios) {
        this.saveAll(Comentarios);
    }
}
