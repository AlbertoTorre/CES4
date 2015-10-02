/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquila.mservices.entities;

import java.io.Serializable;
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
 * 
 */
@Entity
@Table(name = "tmq_servicio")
public class Servicio implements Serializable{
    @TableGenerator(
            table ="tmq_sq",
            name ="tmq_ds",//referencia
            pkColumnName="tmq_seq",// columna con el nombre de sequencia
            valueColumnName="tmq_val",// valor de la secuencia actual
            pkColumnValue="tmq_ds_sq",//Nombre de la secuencia
            initialValue = 1,
            allocationSize=1
    )
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tmq_ds")
    @Column(name = "dni_id")
    private Integer id;
    
    @Column(name = "ds_nombre")
    private String nombre;
    
    @Column(name = "ds_observacion")
    private String observacion;
    
    @OneToOne
    @JoinColumn(name="IdServicio")
    private DetalleServicio detalleServicio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public DetalleServicio getDetalleServicio() {
        return detalleServicio;
    }

    public void setDetalleServicio(DetalleServicio detalleServicio) {
        this.detalleServicio = detalleServicio;
    }
    
    
    
}
