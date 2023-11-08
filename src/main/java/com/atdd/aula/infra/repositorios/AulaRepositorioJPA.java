package com.atdd.aula.infra.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atdd.aula.dominio.entidades.Aula;
import com.atdd.aula.dominio.repositorios.AulaRepositorio;

public interface AulaRepositorioJPA extends JpaRepository<Aula, Long>, AulaRepositorio {
    default Aula getAulaPorId(long id) {
        Optional<Aula> Aula = this.findById(id);

        if (Aula.isEmpty()) {
            return null;
        }

        return Aula.get();
    }

    default List<Aula> getAulas() {
        return this.findAll();
    }

    default Aula salvarAula(Aula Aula) {
        return this.save(Aula);
    }

    default List<Aula> salvarAula(List<Aula> Aulas) {
        return this.saveAll(Aulas);
    }
}
