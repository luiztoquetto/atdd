package com.atdd.aula.dominio.repositorios;

import java.util.List;

import com.atdd.aula.dominio.entidades.Aula;

public interface AulaRepositorio {
    abstract Aula getAulaPorId(long id);

    abstract List<Aula> getAulas();

    abstract void salvarAula(Aula Aula);

    abstract void salvarAula(List<Aula> Aulas);
}
