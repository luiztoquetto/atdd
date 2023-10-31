package com.atdd.curso;

import java.util.Arrays;
import java.util.List;

public class CursoRepositorio {
    private List<Curso> cursos = Arrays.asList(
            new Curso("Curso 1", 1),
            new Curso("Curso 2", 2),
            new Curso("Curso 3", 3),
            new Curso("Curso 4", 4),
            new Curso("Curso 5", 5));

    public Curso getCursoPorId(int id) {
        for (Curso curso : cursos) {
            if (curso.getId() == id) {
                return curso;
            }
        }
        return null;
    }

    public List<Curso> getCursos() {
        return cursos;
    }
}
