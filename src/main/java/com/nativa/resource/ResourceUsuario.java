package com.nativa.resource;

import com.nativa.dto.in.UserDTO;
import com.nativa.model.Usuario;
import com.nativa.service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class ResourceUsuario {
    @Autowired private ServiceUsuario service;
    @GetMapping
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("Hello World!");
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDTO userDTO){
        Usuario usuario = service.createUser(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }
}
