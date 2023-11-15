package com.atdd.usuario.presenter.dtos.outputs;

import com.atdd.usuario.dominio.entidades.Usuario;

import lombok.Getter;
@Getter
public class UsuarioOutputDto {

	public UsuarioOutputDto(Usuario usuario) {
        id = usuario.getId();
        name = usuario.getName();
        quantidadeDeMatriculasDisponiveis = usuario.getQuantidadeDeMatriculasDisponiveis();
    }

    private Long id;
    private String name;
    private Integer quantidadeDeMatriculasDisponiveis;
	
}
