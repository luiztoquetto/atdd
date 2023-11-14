package com.atdd.aula.presenter.dtos.outputs;

import com.atdd.aula.dominio.entidades.Aula;

import lombok.Getter;

@Getter
public class AulaOutputDto {
    public AulaOutputDto(Aula aula) {
        id = aula.getId();
        name = aula.getName();
    }

    private Long id;
    private String name;
}