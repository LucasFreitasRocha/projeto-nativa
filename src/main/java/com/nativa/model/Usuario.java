package com.nativa.model;

import com.nativa.dto.in.CadastroDTO;
import com.nativa.utils.Generate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;

@Entity
public class Usuario extends EntityModel implements UserDetails {


    @Id
    private String id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

    public Usuario() {
    }

    public Usuario(CadastroDTO cadastroDTO) {
        super();
        this.id = Generate.uuid();
        this.email = cadastroDTO.getEmail();
        this.name = cadastroDTO.getName();
        this.password = Generate.hashPassword(cadastroDTO.getPassword());
    }
    public void update(CadastroDTO cadastroDTO) {
        this.email = cadastroDTO.getEmail();
        this.name = cadastroDTO.getName();
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = Generate.hashPassword(password);
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.email;
    }



    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }


}
