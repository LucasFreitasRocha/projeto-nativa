package com.nativa.service;


import com.nativa.dto.in.LoginDTO;
import com.nativa.dto.out.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private TokenService tokenService;

    public TokenDto authenticate(LoginDTO loginDto, AuthenticationManager authManager) {
        UsernamePasswordAuthenticationToken dadosLogin = loginDto.converter();
        Authentication authentication = authManager.authenticate(dadosLogin);
        String token = tokenService.gerarToken(authentication);
        return new TokenDto(token, "Bearer");


    }
}
