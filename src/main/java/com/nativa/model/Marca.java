package com.nativa.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nativa.dto.in.MarcaDTO;
import com.nativa.utils.Generate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Marca  extends EntityModel {

    @Id
    private String id;
    @Column(unique = true, nullable = false)
    private String name;

    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "marca")
    private List<Patrimonio> patrimonios = new ArrayList<Patrimonio>();

    public Marca() {
    }

    public Marca(MarcaDTO marcaDTO) {
        super();
        this.id = Generate.uuid();
        this.name = marcaDTO.getName();
    }

    public List<Patrimonio> getPatrimonios() {
        return patrimonios;
    }

    public void setPatrimonios(List<Patrimonio> patrimonios) {
        this.patrimonios = patrimonios;
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
