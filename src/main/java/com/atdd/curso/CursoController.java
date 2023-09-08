package com.atdd.curso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atdd.matricula.Matricula;
import com.atdd.usuario.Usuario;
import com.atdd.usuario.UsuarioRepositorio;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cursos")
@AllArgsConstructor
public class CursoController {

    private final UsuarioRepositorio usuarioRepositorio;
    private final CursoRepositorio cursoRepositorio;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void realizarMatricula(
            @RequestParam(value = "usuarioId", required = true) int usuarioId,
            @RequestParam(value = "cursoId", required = true) int cursoId) {
        Usuario usuario = usuarioRepositorio.getUsuarioPorId(usuarioId);
        usuario.adicionarMatricula(cursoId);
    }

}
