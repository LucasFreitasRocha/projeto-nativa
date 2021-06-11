package com.nativa.dto.out;

import com.nativa.model.Usuario;
import org.springframework.data.domain.Page;

public class UsuarioDTO {
    String name;
    String email;
    String id;
    public UsuarioDTO(Usuario obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.email = obj.getUsername();
    }

    public UsuarioDTO() {
    }

    public static Page<UsuarioDTO> converter(Page<Usuario> usuarioPage) {
        return usuarioPage.map(UsuarioDTO::new);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
