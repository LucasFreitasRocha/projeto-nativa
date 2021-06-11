package com.nativa.service;

import com.nativa.dto.in.UserDTO;
import com.nativa.exceptions.BadRequestException;
import com.nativa.model.Usuario;
import com.nativa.repository.RepositoryUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceUsuario {
        @Autowired private RepositoryUsuario repositoryUsuario;

    public Usuario createUser(UserDTO userDTO) {
        verificaEmail(userDTO.getEmail());
        Usuario usuario = new Usuario(userDTO);
        return repositoryUsuario.save(usuario);

    }

    public void verificaEmail(String email){
        Optional<Usuario> optUsuario = repositoryUsuario.findByEmail(email);
        if(optUsuario.isPresent()){
            throw new BadRequestException(" EMAIL já cadastrados.");
        }
    }
}
