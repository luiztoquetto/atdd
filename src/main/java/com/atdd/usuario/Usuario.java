package com.atdd.usuario;
import com.atdd.matricula.Matricula;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
class Usuario {
    private String name;
    private Integer id;
    private List<Matricula> matriculas;
}
