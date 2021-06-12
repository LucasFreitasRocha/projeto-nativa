package com.nativa.dto.in;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDTO {

    private String email;
    private String password;


    public LoginDTO() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(this.email, this.password);
    }

}
