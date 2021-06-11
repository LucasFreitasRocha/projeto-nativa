package com.nativa.dto.in;

import com.nativa.annotation.EmailValidation;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserDTO {

    @NotNull(message = "escolha um nome")
    @NotBlank(message = "Nome não pode ficar em branco")
    String name;
    @NotNull(message = "Email obrigatório.")
    @EmailValidation(message = "Email inválido.")
    String email;
    @NotBlank(message = "senha obrigatoria.")
    @Length(min = 8 , max = 20, message = "a senha deve no minimo 8 e no maximo 20")
    String password;

    public UserDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
