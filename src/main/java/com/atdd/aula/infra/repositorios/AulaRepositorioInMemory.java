package com.atdd.aula.infra.repositorios;

import java.util.ArrayList;
import java.util.List;

import com.atdd.aula.dominio.entidades.Aula;
import com.atdd.aula.dominio.repositorios.AulaRepositorio;

public class AulaRepositorioInMemory implements AulaRepositorio {
    private List<Aula> aulas = new ArrayList<>();

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

    public void salvarAula(Aula Aula) {
        aulas.add(Aula);
    }

    public void salvarAula(List<Aula> Aula) {
        aulas.addAll(Aula);
    }
}
