package com.nativa.service;

import com.nativa.dto.in.CadastroDTO;
import com.nativa.dto.out.UsuarioDTO;
import com.nativa.exceptions.BadRequestException;
import com.nativa.exceptions.ObjectNotFoundException;
import com.nativa.model.Usuario;
import com.nativa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
        @Autowired private UsuarioRepository usuarioRepository;

    public Usuario createUser(CadastroDTO cadastroDTO) {
        verificaEmail(cadastroDTO.getEmail());
        Usuario usuario = new Usuario(cadastroDTO);
        return usuarioRepository.save(usuario);

    }

    private void verificaEmail(String email){
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(email);
        if(optUsuario.isPresent()){
            throw new BadRequestException(" EMAIL já cadastrados.");
        }
    }



    public Page<UsuarioDTO> index(Pageable paginacao) {

            Page<Usuario> pageUsuario = usuarioRepository.findAll(paginacao);
            return UsuarioDTO.converter(pageUsuario);
    }

    public UsuarioDTO findByEmail(String email) {
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(email);
        if(!optUsuario.isPresent()){
            throw  new ObjectNotFoundException(
                    "Usuario não encrontrado com esse email: " + email);
        }
        return new UsuarioDTO(optUsuario.get());
    }

    public List<UsuarioDTO> findByName(String name) {
        List<Usuario> users = usuarioRepository.findByNameContaining(name);
        return UsuarioDTO.converter(users);
    }
}
