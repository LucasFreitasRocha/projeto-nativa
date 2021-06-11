package com.nativa.model;

import com.nativa.dto.in.MarcaDTO;
import com.nativa.utils.Generate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Marca  extends EntityModel {

    @Id
    private String id;
    private String name;

    public Marca() {
    }

    public Marca(MarcaDTO marcaDTO) {
        super();
        this.id = Generate.uuid();
        this.name = marcaDTO.getName();
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
