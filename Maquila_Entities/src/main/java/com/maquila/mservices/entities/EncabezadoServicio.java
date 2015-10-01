/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquila.mservices.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "tmq_encabezado_servicio")
public class EncabezadoServicio implements Serializable {
    @TableGenerator(
            table ="tmq_sq",
            name ="tmq_es",//referencia
            pkColumnName="tmq_seq",// columna con el nombre de sequencia
            valueColumnName="tmq_val",// valor de la secuencia actual
            pkColumnValue="tmq_es_sq",//Nombre de la secuencia
            initialValue = 1,
            allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tmq_es")
    @Column(name = "dni_id")
    private Integer id;
    
    @Column(name = "nm_valor_total")
    private BigInteger valorTotal;
    
    @Column(name = "nm_horas_totales_laboradas")
    private Integer horasTotalesLaboradas;
    
    @Column(name = "fe_servicio")
    @Temporal(TemporalType.DATE)
    private Date fechaServicio;
    
    @OneToMany(mappedBy="id")
    private List<Cliente> cliente;
    
    @OneToMany(mappedBy="id")
    private List<Empleado> empleado;    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigInteger valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getHorasTotalesLaboradas() {
        return horasTotalesLaboradas;
    }

    public void setHorasTotalesLaboradas(Integer horasTotalesLaboradas) {
        this.horasTotalesLaboradas = horasTotalesLaboradas;
    }

    public Date getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(Date fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public List<Cliente> getCliente() {
        return cliente;
    }

    public void setCliente(List<Cliente> cliente) {
        this.cliente = cliente;
    }

    public List<Empleado> getEmpleado() {
        return empleado;
    }

    public void setEmpleado(List<Empleado> empleado) {
        this.empleado = empleado;
    }
}
