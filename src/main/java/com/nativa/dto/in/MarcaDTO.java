package com.nativa.dto.in;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MarcaDTO {
    @NotNull(message = "escolha um nome")
    @NotBlank(message = "Nome não pode ficar em branco")
    private String name;

    public MarcaDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
