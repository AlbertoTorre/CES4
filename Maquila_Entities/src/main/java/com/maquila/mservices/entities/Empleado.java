/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquila.mservices.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alex
 * 
 */
@Entity
@Table(name = "tmq_empleados")
public class Empleado implements Serializable {
    @TableGenerator(
            table ="tmq_sq",
            name ="tmq_em",//referencia
            pkColumnName="tmq_seq",// columna con el nombre de sequencia
            valueColumnName="tmq_val",// valor de la secuencia actual
            pkColumnValue="tmq_em_sq",//Nombre de la secuencia
            initialValue = 1,
            allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tmq_em")
    @Column(name = "dni_id")
    private Integer id;
        
    @Column(name = "ds_cargo")
    private String cargo;
   
    @Column(name = "fe_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
   
    @Column(name = "fe_retiro")
    @Temporal(TemporalType.DATE)
    private Date fechaRetiro;
    
    @OneToOne
    @JoinColumn(name="empleado")
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(Date fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
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
