package com.atdd.aula.presenter.dtos.inputs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AulaInputDto {
    @NotBlank(message = "É necessário enviar um nome para a aula")
    private String name;

    @NotNull(message = "É necessário relacionar essa aula à um curso")
    private long cursoId;
}
