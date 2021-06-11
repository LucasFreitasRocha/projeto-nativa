package com.nativa.model;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class EntityModel implements Serializable {

    public abstract void setId(String id);

    public abstract String getId();



    public String toString(){
        return String.valueOf(this.getId());
    }

    public int hashCode() {
        return this.toString().hashCode();
    }

    public boolean equals(Object obj) {
        return this.toString().equals(String.valueOf(obj));
    }

}
