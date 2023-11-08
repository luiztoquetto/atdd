package com.atdd.curso.presenter.dtos.inputs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoInputDto {
    @NotBlank(message = "É necessário enviar um nome para o curso")
    private String name;
}
