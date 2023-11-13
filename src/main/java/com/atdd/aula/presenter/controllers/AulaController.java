package com.atdd.aula.presenter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atdd.aula.dominio.entidades.Aula;
import com.atdd.aula.dominio.repositorios.AulaRepositorio;
import com.atdd.aula.presenter.dtos.inputs.AulaInputDto;
import com.atdd.aula.presenter.dtos.outputs.AulaOutputDto;
import com.atdd.curso.dominio.entidades.Curso;
import com.atdd.curso.dominio.repositorios.CursoRepositorio;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/aulas")
@AllArgsConstructor
public class AulaController {

    @Autowired
    private AulaRepositorio aulaRepositorio;
    
    @Autowired
    private CursoRepositorio cursoRepositorio;

    // Vinícius Martins Granso - 224072
    @GetMapping("")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<AulaOutputDto> getAulas() {
        return aulaRepositorio.getAulas().stream().map(AulaOutputDto::new).toList();
    }

    // Vinícius Martins Granso - 224072
    @PostMapping
    public AulaOutputDto createAula(@Valid @RequestBody AulaInputDto AulaCreateDTO) {
        Curso curso = cursoRepositorio.getCursoPorId(AulaCreateDTO.getCursoId());
        Aula aula = new Aula(AulaCreateDTO.getName(), curso);

        Aula aulaSalva = aulaRepositorio.salvarAula(aula);

        return new AulaOutputDto(aulaSalva);
    }
}
