package com.atdd.aula.dominio.repositorios;

import java.util.List;

import com.atdd.aula.dominio.entidades.Aula;

public interface AulaRepositorio {
    Aula getAulaPorId(long id);

    List<Aula> getAulas();

    Aula salvarAula(Aula Aula);

    List<Aula> salvarAula(List<Aula> Aulas);
}
