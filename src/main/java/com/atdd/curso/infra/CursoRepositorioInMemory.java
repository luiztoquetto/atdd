package com.atdd.curso.infra;

import java.util.Arrays;
import java.util.List;

import com.atdd.curso.dominio.entidades.Curso;
import com.atdd.curso.dominio.repositorios.CursoRepositorio;

public class CursoRepositorioInMemory implements CursoRepositorio {
    private List<Curso> cursos = Arrays.asList(
            new Curso(1, "Curso 1"),
            new Curso(2, "Curso 2"),
            new Curso(3, "Curso 3"),
            new Curso(4, "Curso 4"),
            new Curso(5, "Curso 5"));

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

    public void salvarCurso(Curso curso) {
        cursos.add(curso);
    }
}
