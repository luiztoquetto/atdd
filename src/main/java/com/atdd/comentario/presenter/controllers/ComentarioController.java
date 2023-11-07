package com.atdd.comentario.presenter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atdd.comentario.dominio.repositorios.ComentarioRepositorio;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/comentarios")
@AllArgsConstructor
public class ComentarioController {

    @Autowired
    private ComentarioRepositorio ComentarioRepositorio;

}
