package com.atdd.aula.presenter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atdd.aula.dominio.repositorios.AulaRepositorio;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/aulas")
@AllArgsConstructor
public class AulaController {

    @Autowired
    private AulaRepositorio AulaRepositorio;

}
