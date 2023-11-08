package com.atdd.aula.infra.repositorios;

import java.util.ArrayList;
import java.util.List;

import com.atdd.aula.dominio.entidades.Aula;
import com.atdd.aula.dominio.repositorios.AulaRepositorio;

public class AulaRepositorioInMemory implements AulaRepositorio {
    private List<Aula> aulas = new ArrayList<>();
    private long nextId = 1;

    public Aula getAulaPorId(long id) {
        for (Aula Aula : aulas) {
            if (Aula.getId() == id) {
                return Aula;
            }
        }
        return null;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public Aula salvarAula(Aula aula) {
        aula.setId(nextId);
        aulas.add(aula);
        nextId++;
        return aula;
    }

    public List<Aula> salvarAula(List<Aula> aulasParaSalvar) {
        for (Aula aula : aulasParaSalvar) {
            aula.setId(nextId);
            aulas.add(aula);
            nextId++;
        }
        return aulasParaSalvar;
    }
}
