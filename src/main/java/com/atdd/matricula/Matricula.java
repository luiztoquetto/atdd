package com.atdd.matricula;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
class Matricula {
    private Double media;
    private Integer usuarioId;
    private Integer cursoId;
}
