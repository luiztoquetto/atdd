package com.atdd.aula.dominio.entidades;

import com.atdd.curso.dominio.entidades.Curso;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Aula {
    public Aula(String name, Curso curso) {
        this.name = name;
        this.curso = curso;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne // Many Aulas can be associated with one Curso
    @JoinColumn(name = "curso_id") // This defines the foreign key column in the Aula table
    private Curso curso;
}
