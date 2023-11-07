package com.atdd.curso.dominio.entidades;

import java.util.ArrayList;
import java.util.List;

import com.atdd.aula.dominio.entidades.Aula;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Curso {
    public Curso(String name) {
        this.name = name;
        this.aulas = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "curso")
    private List<Aula> aulas;
}
