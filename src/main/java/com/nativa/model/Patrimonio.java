package com.nativa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nativa.dto.in.PatrimonioDTO;
import com.nativa.utils.Generate;

@Entity
public class Patrimonio  extends EntityModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    private  String id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(name = "ntombo", updatable = false)
    private Integer nTombo;

    @JsonBackReference
    @ManyToOne
    @JoinColumn( name = "marcaid")
    private Marca marca;

    public Patrimonio() {
    }

    public Patrimonio(PatrimonioDTO patrimonioDTO) {
        super();
        this.id = Generate.uuid();
        this.name = patrimonioDTO.getName();
        this.description = patrimonioDTO.getDescription();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getnTombo() {
        return nTombo;
    }

    public void setnTombo(Integer nTombo) {
        this.nTombo = nTombo;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
