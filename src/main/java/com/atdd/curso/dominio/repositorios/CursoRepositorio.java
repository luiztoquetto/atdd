package com.atdd.curso.dominio.repositorios;

import java.util.List;

import com.atdd.curso.dominio.entidades.Curso;

public interface CursoRepositorio {
    abstract Curso getCursoPorId(long id);

    abstract List<Curso> getCursos();

    abstract void salvarCurso(Curso curso);

    abstract void salvarCurso(List<Curso> cursos);
}
