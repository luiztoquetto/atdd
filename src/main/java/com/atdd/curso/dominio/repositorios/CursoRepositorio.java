package com.atdd.curso.dominio.repositorios;

import java.util.List;

import com.atdd.curso.dominio.entidades.Curso;

public interface CursoRepositorio {
    Curso getCursoPorId(long id);

    List<Curso> getCursos();

    Curso salvarCurso(Curso curso);

    List<Curso> salvarCurso(List<Curso> cursos);
}
