package com.atdd.aula.presenter.dtos.inputs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AulaInputDto {
    @NotBlank(message = "É necessário enviar um nome para a aula")
    private String name;
}
