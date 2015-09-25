package com.mycompany.maquila_entities;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TMQ_SERVICIO")

public class Servicio implements Serializable{
    @Id
    @Access(AccessType.FIELD)
    @Column (name="DNI")
    private Integer id;
    @Column (name="DS_DESCRIPCION")
    private String descripcion;
    @Column(name="NM_VALOR")
    private Integer valor;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    
}
