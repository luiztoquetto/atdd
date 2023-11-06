package com.atdd;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

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
	private Environment environment;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private CursoRepositorio cursoRepositorio;

	@Bean
	public ApplicationListener<ApplicationReadyEvent> initializeDatabase() {
		return event -> {
			String databaseDriver = environment.getProperty("spring.datasource.driverClassName");

			if (!databaseDriver.equals("org.h2.Driver")) {
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

			cursoRepositorio.salvarCurso(cursos);
			usuarioRepositorio.salvarUsuario(usuarios);
		};
	}

}
