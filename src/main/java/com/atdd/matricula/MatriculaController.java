package com.atdd.matricula;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MatriculaController {
   
    private List<Matricula> matriculas = Arrays.asList(
        new Matricula(10.0, 1 , 1),
        new Matricula(9.0, 2 , 2),
        new Matricula(8.0, 3 , 3),
        new Matricula(7.0, 4 , 4),
        new Matricula(6.0, 5 , 5)
    );

    @GetMapping("/matriculas")
    List<Matricula> all() {
        return matriculas;
    }


}
