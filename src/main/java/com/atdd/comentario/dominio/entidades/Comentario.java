package com.atdd.comentario.dominio.entidades;

import com.atdd.aula.dominio.entidades.Aula;
import com.atdd.usuario.dominio.entidades.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comentario {
    public Comentario(String mensagem, Usuario usuario, Aula aula) {
        this.mensagem = mensagem;
        this.usuario = usuario;
        this.aula = aula;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "aula_id")
    private Aula aula;
}
