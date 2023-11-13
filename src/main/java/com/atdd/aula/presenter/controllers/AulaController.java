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

    @GetMapping("/aulaId")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void getAula(
        @RequestParam(value = "aulaId", required = true) int aulaId
        @RequestParam(value = "aula", required = true) Aula aula) {
            Aula aulaNaoEncontrada = aula.getAula(aulaId);

            if (aulaNaoEncontrada == null) {
                throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Essa aula não existe.");
            }
        }

    // Vinícius Martins Granso - 200109
    @PostMapping
    public AulaOutputDto createAula(@Valid @RequestBody AulaInputDto AulaCreateDTO, CursoInputDto CursoInputDto) {
        Aula aula = new Aula(AulaCreateDTO.getName(), CursoInputDto.);

        Aula aulaSalva = aulaRepositorio.salvarAula(aula);

        return new AulaOutputDto(aulaSalva);
    }
}
