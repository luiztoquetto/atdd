package com.atdd.usuario.dominio.entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Usuario {
    public Usuario(String name,
            int quantidadeDeMatriculasDisponiveis,
            List<Matricula> matriculas) {
        this.name = name;
        this.quantidadeDeMatriculasDisponiveis = quantidadeDeMatriculasDisponiveis;
        this.matriculas = matriculas;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Integer quantidadeDeMatriculasDisponiveis;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Matricula> matriculas = new ArrayList<>();

    public void adicionarMatricula(long cursoId) {
        List<Matricula> matriculasAtuais = new ArrayList<Matricula>(matriculas);

        Matricula novaMatricula = new Matricula();
        novaMatricula.setCursoId(cursoId);
        novaMatricula.setUsuario(this);
        matriculasAtuais.add(novaMatricula);

        matriculas = matriculasAtuais;
    }

    public Matricula getMatriculaPorCursoId(int id) {
        for (Matricula matricula : matriculas)
            if (matricula.getCursoId() == id)
                return matricula;
        return null;
    }

    public void adicionarMatriculasDisponiveis(int qtd) {
        quantidadeDeMatriculasDisponiveis += qtd;
    }

    public void removerMatriculasDisponiveis(int qtd) {
        quantidadeDeMatriculasDisponiveis -= qtd;
    }
}
