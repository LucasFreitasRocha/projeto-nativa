package com.nativa.service;

import com.nativa.dto.in.UserDTO;
import com.nativa.exceptions.BadRequestException;
import com.nativa.model.Usuario;
import com.nativa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
        @Autowired private UsuarioRepository usuarioRepository;

    public Usuario createUser(UserDTO userDTO) {
        verificaEmail(userDTO.getEmail());
        Usuario usuario = new Usuario(userDTO);
        return usuarioRepository.save(usuario);

    }

    public void verificaEmail(String email){
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(email);
        if(optUsuario.isPresent()){
            throw new BadRequestException(" EMAIL já cadastrados.");
        }
    }
}
