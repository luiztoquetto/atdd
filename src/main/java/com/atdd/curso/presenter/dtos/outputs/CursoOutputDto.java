package com.atdd.curso.presenter.dtos.outputs;

import com.atdd.curso.dominio.entidades.Curso;

import lombok.Getter;

@Getter
public class CursoOutputDto {
    public CursoOutputDto(Curso curso) {
        id = curso.getId();
        name = curso.getName();
    }

    private Integer id;
    private String name;
}
