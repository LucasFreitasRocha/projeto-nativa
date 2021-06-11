package com.nativa.dto.in;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PatrimonioDTO {

    @NotNull(message = "escolha um nome")
    @NotBlank(message = "Nome não pode ficar em branco")
    private String name;
    private String description;
    @NotNull(message = "informe o id da marca")
    @NotBlank(message = "Id da marca não pode ficar em branco")
    private String marcaId;

    public PatrimonioDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(String marcaId) {
        this.marcaId = marcaId;
    }
}
