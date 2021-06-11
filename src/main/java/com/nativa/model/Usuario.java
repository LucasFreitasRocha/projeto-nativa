package com.nativa.model;

import com.nativa.dto.in.UserDTO;
import com.nativa.utils.Generate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario extends EntityModel {


    @Id
    private String id;
    private String email;
    private String name;
    private String password;

    public Usuario() {
    }

    public Usuario(UserDTO userDTO) {
        super();
        this.id = Generate.uuid();
        this.email = userDTO.getEmail();
        this.name = userDTO.getName();
        this.password = Generate.hashPassword(userDTO.getPassword());
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
