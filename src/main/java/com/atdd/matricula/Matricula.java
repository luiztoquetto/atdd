package com.atdd.matricula;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Matricula {
    private Double notaA;
    private Double notaB;
    private Double notaC;
    private Integer usuarioId;
    private Integer cursoId;
}
