package com.atdd.usuario;

import com.atdd.matricula.Matricula;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Usuario {
    private String name;
    private Integer id;
    private List<Matricula> matriculas;
    private Integer quantidadeDeMatriculasDisponiveis;

    public void adicionarMatricula(int cursoId) {
        List<Matricula> matriculasDoUsuario = new ArrayList<Matricula>(matriculas);
        Matricula novaMatricula = new Matricula(0.0, 0.0, 0.0, cursoId);

        matriculasDoUsuario.add(novaMatricula);

        this.matriculas = matriculasDoUsuario;
    }

    public Matricula getMatriculaPorCursoId(int id) {
        for (Matricula matricula : matriculas)
            if (matricula.getCursoId() == id)
                return matricula;

        return null;
    }

    public void adicionarMatriculasDisponiveis(int qtd) {
        setQuantidadeDeMatriculasDisponiveis(getQuantidadeDeMatriculasDisponiveis() + qtd);
    }

    public void removerMatriculasDisponiveis(int qtd) {
        setQuantidadeDeMatriculasDisponiveis(getQuantidadeDeMatriculasDisponiveis() - qtd);
    }
}
