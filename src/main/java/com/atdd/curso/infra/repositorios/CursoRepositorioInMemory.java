package com.atdd.curso.infra.repositorios;

import java.util.Arrays;
import java.util.List;

import com.atdd.curso.dominio.entidades.Curso;
import com.atdd.curso.dominio.repositorios.CursoRepositorio;

public class CursoRepositorioInMemory implements CursoRepositorio {
    private List<Curso> cursos = Arrays.asList(
            new Curso("Curso 1"),
            new Curso("Curso 2"),
            new Curso("Curso 3"),
            new Curso("Curso 4"),
            new Curso("Curso 5"));

    public Curso getCursoPorId(long id) {
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

    public void salvarCurso(List<Curso> curso) {
        cursos.addAll(curso);
    }
}
