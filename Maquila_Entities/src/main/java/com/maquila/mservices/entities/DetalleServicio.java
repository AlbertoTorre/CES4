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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author 
 */
@Entity
@Table(name = "tmq_detalle_servicio")
public class DetalleServicio implements Serializable {
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
    @Access(AccessType.FIELD)
    private Integer Id;
    
    @JoinColumn(name = "idEmpleado", referencedColumnName = "dni_id")
    @ManyToOne
    private Empleado idEmpleado;
    
    @Column(name = "nm_id_servicio")
    private Integer IdServicio;
    @Column(name = "nm_horas")
    private Integer Horas;
    @Column(name = "nm_valor_hora")
    private Integer ValorHora;
    @Column(name = "ds_observacion")
    private String  Observacion;
    
    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Integer getIdServicio() {
        return IdServicio;
    }

    public void setIdServicio(Integer IdServicio) {
        this.IdServicio = IdServicio;
    }

    public Integer getHoras() {
        return Horas;
    }

    public void setHoras(Integer Horas) {
        this.Horas = Horas;
    }

    public Integer getValorHora() {
        return ValorHora;
    }

    public void setValorHora(Integer ValorHora) {
        this.ValorHora = ValorHora;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }
    
}
