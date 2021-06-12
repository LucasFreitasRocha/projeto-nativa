package com.nativa.resource;

import com.nativa.dto.in.LoginDTO;
import com.nativa.dto.out.TokenDto;
import com.nativa.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    private AuthService authService;
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping
    public ResponseEntity<TokenDto> authenticate(@RequestBody LoginDTO loginDto){
        return ResponseEntity.ok(authService.authenticate(loginDto, authManager));
    }
}
