package com.atdd.comentario.dominio.repositorios;

import java.util.List;

import com.atdd.comentario.dominio.entidades.Comentario;

public interface ComentarioRepositorio {
    abstract Comentario getComentarioPorId(long id);

    abstract List<Comentario> getComentariosPorAulaId(long aulaId);

    abstract void salvarComentario(Comentario Comentario);

    abstract void salvarComentario(List<Comentario> Comentarios);
}
