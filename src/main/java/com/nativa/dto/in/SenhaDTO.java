package com.nativa.dto.in;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class SenhaDTO {
    @NotBlank(message = "senha obrigatoria.")
    @Length(min = 8 , max = 20, message = "a senha deve no minimo 8 e no maximo 20")
    String password;

    public SenhaDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
