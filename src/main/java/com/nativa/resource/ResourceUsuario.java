package com.nativa.resource;

import com.nativa.dto.in.UserDTO;
import com.nativa.dto.out.TokenDto;
import com.nativa.model.Usuario;
import com.nativa.service.TokenService;
import com.nativa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.management.remote.JMXAuthenticator;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/user")
public class ResourceUsuario {
    @Autowired private UsuarioService service;
    @Autowired private AuthenticationManager authManager;
    @Autowired private TokenService tokenService;
    @GetMapping
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("Hello World!");
    }

    @PostMapping
    public ResponseEntity<TokenDto> createUsuario(@Valid @RequestBody UserDTO userDTO){
        UsernamePasswordAuthenticationToken dadosLogin = userDTO.converter();
        Usuario usuario = service.createUser(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        try {

            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.created(uri).body(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
