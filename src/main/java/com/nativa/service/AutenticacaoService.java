package com.nativa.service;

import com.nativa.model.Usuario;
import com.nativa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optUsuario = repository.findByEmail(username);
        if (optUsuario.isPresent()) {
            return optUsuario.get();
        }

        throw new UsernameNotFoundException("Dados inválidos!");
    }

}