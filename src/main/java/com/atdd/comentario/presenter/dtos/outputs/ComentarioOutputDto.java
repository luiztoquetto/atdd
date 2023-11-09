package com.atdd.comentario.presenter.dtos.outputs;

import com.atdd.comentario.dominio.entidades.Comentario;

import lombok.Getter;

@Getter
public class ComentarioOutputDto {
    public ComentarioOutputDto(Comentario comentario) {
        id = comentario.getId();
        mensagem = comentario.getMensagem();
    }

    private Long id;
    private String mensagem;
}
