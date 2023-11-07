package com.atdd.comentario.infra.repositorios;

import java.util.ArrayList;
import java.util.List;

import com.atdd.comentario.dominio.entidades.Comentario;
import com.atdd.comentario.dominio.repositorios.ComentarioRepositorio;

public class ComentarioRepositorioInMemory implements ComentarioRepositorio {
    private List<Comentario> comentarios = new ArrayList<>();

    public Comentario getComentarioPorId(long id) {
        for (Comentario Comentario : comentarios) {
            if (Comentario.getId() == id) {
                return Comentario;
            }
        }
        return null;
    }

    public List<Comentario> getComentariosPorAulaId(long aulaId) {
        return comentarios.stream().filter(comentario -> comentario.getAula().getId() == aulaId).toList();
    }

    public void salvarComentario(Comentario Comentario) {
        comentarios.add(Comentario);
    }

    public void salvarComentario(List<Comentario> Comentario) {
        comentarios.addAll(Comentario);
    }
}
