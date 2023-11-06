package com.atdd.usuario.dominio.entidades;

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
    private Integer cursoId;

    public Double getMedia() {
        return (notaA + notaB + notaC) / 3;
    }
}
