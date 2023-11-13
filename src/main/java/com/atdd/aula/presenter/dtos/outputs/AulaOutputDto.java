package com.atdd.aula.presenter.dtos.outputs;

import com.atdd.aula.dominio.entidades.Aula;

import lombok.Getter;

@Getter
public class AulaOutputDto {
    public AulaOutputDto(Aula curso) {
        id = aula.getId();
        name = aula.getName();
    }

    private Long id;
    private String name;
}