/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquila.mservices.entities;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "tmq_cliente")//como se llama en la base de datos
public class Cliente implements Serializable{
    @TableGenerator(
            table ="tmq_sq",
            name ="tmq_cl",//referencia
            pkColumnName="tmq_seq",// columna con el nombre de sequencia
            valueColumnName="tmq_val",// valor de la secuencia actual
            pkColumnValue="tmq_cl_sq",//Nombre de la secuencia
            initialValue = 1,
            allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tmq_cl")
    @Column(name = "dni_id")
    private Integer id;
    
    @Column(name = "ds_correo")
    private String correo;
    
    @Column(name = "nm_telefono")
    private Integer telefono;
    
    @Column(name = "ds_direccion")
    private String direccion;
    
    @OneToOne
    @JoinColumn(name="cliente")
    private EncabezadoServicio encabezadoServicio;
    
    @OneToOne
    @JoinColumn(name = "id")
    private Persona persona;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public EncabezadoServicio getEncabezadoServicio() {
        return encabezadoServicio;
    }

    public void setEncabezadoServicio(EncabezadoServicio encabezadoServicio) {
        this.encabezadoServicio = encabezadoServicio;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    
}
