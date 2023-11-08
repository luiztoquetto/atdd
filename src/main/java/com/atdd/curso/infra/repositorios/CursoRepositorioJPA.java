package com.atdd.curso.infra.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atdd.curso.dominio.entidades.Curso;
import com.atdd.curso.dominio.repositorios.CursoRepositorio;

public interface CursoRepositorioJPA extends JpaRepository<Curso, Long>, CursoRepositorio {
    default Curso getCursoPorId(long id) {
        Optional<Curso> Curso = this.findById(id);

        if (Curso.isEmpty()) {
            return null;
        }

        return Curso.get();
    }

    default List<Curso> getCursos() {
        return this.findAll();
    }

    default Curso salvarCurso(Curso curso) {
        return this.save(curso);
    }

    default List<Curso> salvarCurso(List<Curso> cursos) {
        return this.saveAll(cursos);
    }
}
