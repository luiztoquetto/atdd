package com.atdd.usuario.presenter.controllers;

import java.util.ArrayList;

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

import com.atdd.usuario.dominio.entidades.Matricula;
import com.atdd.usuario.dominio.entidades.Usuario;
import com.atdd.usuario.dominio.repositorios.UsuarioRepositorio;
import com.atdd.usuario.presenter.dtos.inputs.UsuarioInputDto;
import com.atdd.usuario.presenter.dtos.outputs.UsuarioOutputDto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {
	
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
	
    // Laura Cecilia - 200343
    @GetMapping("")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioOutputDto> getUsuarios() {
        return usuarioRepositorio.getUsuarios().stream().map(UsuarioOutputDto::new).toList();
    }

    // Laura Cecilia - 200343
    @PostMapping
    public UsuarioOutputDto createUsuario(@Valid @RequestBody UsuarioInputDto UsuarioCreateDTO) {
    
    	List <Matricula> matricula = new ArrayList<>();
        Usuario usuario = new Usuario(UsuarioCreateDTO.getName(),UsuarioCreateDTO.getQuantidadeDeMatriculasDisponiveis(),matricula);
        
        
        Usuario usuarioSalvo = usuarioRepositorio.salvarUsuario(usuario);

        return new UsuarioOutputDto(usuarioSalvo);
    }

}
