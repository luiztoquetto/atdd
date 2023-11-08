package com.atdd.comentario.infra.repositorios;

import java.util.ArrayList;
import java.util.List;

import com.atdd.comentario.dominio.entidades.Comentario;
import com.atdd.comentario.dominio.repositorios.ComentarioRepositorio;

public class ComentarioRepositorioInMemory implements ComentarioRepositorio {
    private List<Comentario> comentarios = new ArrayList<>();
    private long nextId = 1;

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

    public Comentario salvarComentario(Comentario comentario) {
        comentario.setId(nextId);
        comentarios.add(comentario);
        nextId++;
        return comentario;
    }

    public List<Comentario> salvarComentario(List<Comentario> comentariosParaSalvar) {
        for (Comentario comentario : comentariosParaSalvar) {
            comentario.setId(nextId);
            comentarios.add(comentario);
            nextId++;
        }
        return comentariosParaSalvar;
    }
}
