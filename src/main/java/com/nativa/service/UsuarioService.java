package com.nativa.service;

import com.nativa.dto.in.CadastroDTO;
import com.nativa.dto.in.SenhaDTO;
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
        @Autowired private TokenService tokenService;

    public Usuario createUser(CadastroDTO cadastroDTO) {
        verificaEmail(cadastroDTO.getEmail());
        Usuario usuario = new Usuario(cadastroDTO);
        return usuarioRepository.save(usuario);

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

    public UsuarioDTO show(String id) {
        return new UsuarioDTO(find(id));
    }

    private Usuario find(String id){
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        if(!optUsuario.isPresent()){
            throw  new ObjectNotFoundException(
                    "Usuario não encrontrado com esse id: " + id);
        }
        return optUsuario.get();
    }
    private void verificaEmail(String email){
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(email);
        if(optUsuario.isPresent()){
            throw new BadRequestException(" EMAIL já cadastrados.");
        }
    }

    public void updateUser(String id, CadastroDTO cadastroDTO) {
        Usuario usuario = find(id);
        usuario.update(cadastroDTO);
        usuarioRepository.save(usuario);
    }

    public void deleteUser(String id) {
        usuarioRepository.delete(find(id));
    }

    public void alterarSenha(String token, SenhaDTO senhaDTO) {
        Usuario usuario = find(tokenService.getIdUser(token.substring(7, token.length())));
        usuario.setPassword(senhaDTO.getPassword());
    }
}
