package com.atdd.usuario.dominio.entidades;

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
public class Matricula {
    public Matricula(double notaA, double notaB, double notaC, long cursoId) {
        this.notaA = notaA;
        this.notaB = notaB;
        this.notaC = notaC;
        this.cursoId = cursoId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double notaA;
    private Double notaB;
    private Double notaC;
    private Long cursoId;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Double getMedia() {
        return (notaA + notaB + notaC) / 3;
    }
    
}
