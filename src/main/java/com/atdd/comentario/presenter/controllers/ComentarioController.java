package com.atdd.comentario.presenter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.atdd.aula.dominio.entidades.Aula;
import com.atdd.aula.dominio.repositorios.AulaRepositorio;
import com.atdd.comentario.dominio.entidades.Comentario;
import com.atdd.comentario.dominio.repositorios.ComentarioRepositorio;
import com.atdd.comentario.presenter.dtos.inputs.ComentarioInputDto;
import com.atdd.comentario.presenter.dtos.outputs.ComentarioOutputDto;
import com.atdd.usuario.dominio.entidades.Usuario;
import com.atdd.usuario.dominio.repositorios.UsuarioRepositorio;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/comentarios")
@AllArgsConstructor
public class ComentarioController {

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private AulaRepositorio aulaRepositorio;

    // Luiz Fernando - 200359
    @GetMapping("{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ComentarioOutputDto getComentarioPorId(
        @PathVariable(value = "id", required = true) int id
    ) {
        Comentario comentario = comentarioRepositorio.getComentarioPorId(id);

        if (comentario == null)
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "O comentário enviado não existe."
            );

        return new ComentarioOutputDto(comentario);
    }

    // Luiz Fernando - 200359
    @PostMapping
    public ComentarioOutputDto postComentario(
        @Valid @RequestBody ComentarioInputDto comentarioCreateDTO
    ) {
        Usuario usuario = usuarioRepositorio.getUsuarioPorId(comentarioCreateDTO.getUsuarioId());

        if (usuario == null)
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "O usuário enviado não existe."
            );

        Aula aula = aulaRepositorio.getAulaPorId(comentarioCreateDTO.getAulaId());

        if (aula == null)
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "A aula enviada não existe."
            );

        Comentario comentario = new Comentario(
            comentarioCreateDTO.getMensagem(),
            usuario,
            aula
        );

        Comentario comentarioSalvo = comentarioRepositorio.salvarComentario(comentario);

        return new ComentarioOutputDto(comentarioSalvo);
    }

}
