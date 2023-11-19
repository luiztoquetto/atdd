package com.atdd;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import com.atdd.aula.dominio.entidades.Aula;
import com.atdd.aula.dominio.repositorios.AulaRepositorio;
import com.atdd.comentario.dominio.entidades.Comentario;
import com.atdd.comentario.dominio.repositorios.ComentarioRepositorio;
import com.atdd.curso.dominio.entidades.Curso;
import com.atdd.curso.dominio.repositorios.CursoRepositorio;
import com.atdd.usuario.dominio.entidades.Matricula;
import com.atdd.usuario.dominio.entidades.Usuario;
import com.atdd.usuario.dominio.repositorios.UsuarioRepositorio;

@SpringBootApplication
public class AtddApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtddApplication.class, args);
	}

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private CursoRepositorio cursoRepositorio;

	@Autowired
	private AulaRepositorio aulaRepositorio;

	@Autowired
	private ComentarioRepositorio comentarioRepositorio;

	@Bean
	public ApplicationListener<ApplicationReadyEvent> initializeDatabase() {
		return event -> {
			List<Usuario> usuariosExistentes = usuarioRepositorio.getUsuarios();

			if (usuariosExistentes.size() > 0) {
				return;
			}

			List<Usuario> usuarios = Arrays.asList(
					new Usuario(
							"Usuario 1",
							1,
							Arrays.asList(
									new Matricula(10.0, 6.0, 10.0, 1),
									new Matricula(9.0, 7.0, 9.0, 2),
									new Matricula(8.0, 8.0, 9.0, 3),
									new Matricula(7.0, 9.0, 5.0, 4),
									new Matricula(6.0, 10.0, 7.0, 5))),
					new Usuario("Usuario 2", 0, Arrays.asList()),
					new Usuario("Usuario 3", 0, Arrays.asList()),
					new Usuario("Usuario 4", 0, Arrays.asList()),
					new Usuario("Usuario 5", 0, Arrays.asList()));

			List<Curso> cursos = Arrays.asList(
					new Curso("Curso 1"),
					new Curso("Curso 2"),
					new Curso("Curso 3"),
					new Curso("Curso 4"),
					new Curso("Curso 5"));

			List<Aula> aulas = Arrays.asList(
					new Aula("Aula 1", cursos.get(0)),
					new Aula("Aula 2", cursos.get(1)),
					new Aula("Aula 2", cursos.get(2)),
					new Aula("Aula 3", cursos.get(3)),
					new Aula("Aula 4", cursos.get(4)));

			List<Comentario> comentarios = Arrays.asList(
					new Comentario("Mensagem de comentario 1", usuarios.get(0), aulas.get(0)),
					new Comentario("Mensagem de comentario 2", usuarios.get(0), aulas.get(0)),
					new Comentario("Mensagem de comentario do usuario 2", usuarios.get(1), aulas.get(1)));

			cursoRepositorio.salvarCurso(cursos);
			usuarioRepositorio.salvarUsuario(usuarios);
			aulaRepositorio.salvarAula(aulas);
			comentarioRepositorio.salvarComentario(comentarios);
		};
	}

}
