package com.atdd.comentario.presenter.dtos.inputs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioInputDto {

    @NotBlank(message = "É necessário enviar uma mensagem")
    private String mensagem;

    @NotNull(message = "É necessário enviar o id do usuário")
    private Long usuarioId;

    @NotNull(message = "É necessário enviar o id da aula")
    private Long aulaId;
    
}
