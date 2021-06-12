package com.nativa.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntityModel implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
