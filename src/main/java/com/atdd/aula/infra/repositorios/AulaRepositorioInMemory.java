package com.atdd.aula.infra.repositorios;

import java.util.ArrayList;
import java.util.List;

import com.atdd.aula.dominio.entidades.Aula;
import com.atdd.aula.dominio.repositorios.AulaRepositorio;

public class AulaRepositorioInMemory implements AulaRepositorio {
    private List<Aula> Aulas = new ArrayList<>();

    public Aula getAulaPorId(long id) {
        for (Aula Aula : Aulas) {
            if (Aula.getId() == id) {
                return Aula;
            }
        }
        return null;
    }

    public List<Aula> getAulas() {
        return Aulas;
    }

    public void salvarAula(Aula Aula) {
        Aulas.add(Aula);
    }

    public void salvarAula(List<Aula> Aula) {
        Aulas.addAll(Aula);
    }
}
