package com.atdd.comentario.dominio.repositorios;

import java.util.List;

import com.atdd.comentario.dominio.entidades.Comentario;

public interface ComentarioRepositorio {
    Comentario getComentarioPorId(long id);

    List<Comentario> getComentariosPorAulaId(long aulaId);

    Comentario salvarComentario(Comentario Comentario);

    List<Comentario> salvarComentario(List<Comentario> Comentarios);
}
