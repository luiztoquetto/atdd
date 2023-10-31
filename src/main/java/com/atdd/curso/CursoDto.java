package com.atdd.curso;

import lombok.Getter;

@Getter
public class CursoDto {
    CursoDto(Curso curso) {
        id = curso.getId();
        name = curso.getName();
    }

    private Integer id;
    private String name;
}
