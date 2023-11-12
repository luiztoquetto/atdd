package com.atdd.usuario.presenter.dtos.inputs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInputDto {
	@NotBlank(message = "É necessário enviar um nome para o usuário")
    private String name;
	@NotBlank(message = "É necessário enviar uma quantidade de matriculas para o usuário")
    private Integer quantidadeDeMatriculasDisponiveis;
	
}
