package com.nativa.service;

import com.nativa.dto.in.CadastroDTO;
import com.nativa.exceptions.BadRequestException;
import com.nativa.model.Usuario;
import com.nativa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {
        @Autowired private UsuarioRepository usuarioRepository;

    public Usuario createUser(CadastroDTO cadastroDTO) {
        verificaEmail(cadastroDTO.getEmail());
        Usuario usuario = new Usuario(cadastroDTO);
        return usuarioRepository.save(usuario);

    }

    public void verificaEmail(String email){
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(email);
        if(optUsuario.isPresent()){
            throw new BadRequestException(" EMAIL já cadastrados.");
        }
    }



    public Page<Usuario>  index( Pageable paginacao) {

            return usuarioRepository.findAll(paginacao);
    }
}
