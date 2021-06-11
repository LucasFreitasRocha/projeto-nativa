package com.nativa.resource;

import com.nativa.dto.in.CadastroDTO;
import com.nativa.dto.out.TokenDto;
import com.nativa.dto.out.UsuarioDTO;
import com.nativa.model.Usuario;
import com.nativa.service.TokenService;
import com.nativa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class ResourceUsuario {
    @Autowired private UsuarioService service;
    @Autowired private AuthenticationManager authManager;
    @Autowired private TokenService tokenService;
    @GetMapping
    public ResponseEntity<?> index(@RequestParam(required = false) String email, @RequestParam(required = false) String name,   Pageable paginacao){
       if( Objects.isNull(email) &&  Objects.isNull(name)){
           Page<Usuario> usuarioPage  = service.index( paginacao);
           return ResponseEntity.ok(UsuarioDTO.converter(usuarioPage));
       }else{
           return ResponseEntity.ok("você colocou nome ou email");
       }

    }

    @PostMapping
    public ResponseEntity<TokenDto> createUsuario(@Valid @RequestBody CadastroDTO cadastroDTO){
        UsernamePasswordAuthenticationToken dadosLogin = cadastroDTO.converter();
        Usuario usuario = service.createUser(cadastroDTO);
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
