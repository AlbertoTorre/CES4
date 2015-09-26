/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquila.mservices.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "tmq_empleados")
public class Empleado implements Serializable {
    @Id
    private Integer dniId;
    @OneToMany(mappedBy="idEmpleado")
    private List<DetalleServicio> detalleServicio;
        
    @Column(name = "ds_cargo")
    private String dsCargo;
   
    @Column(name = "fe_ingreso")
    @Temporal(TemporalType.DATE)
    private Date feIngreso;
   
    @Column(name = "fe_retiro")
    @Temporal(TemporalType.DATE)
    private Date feRetiro;
    
    @OneToMany
    @JoinColumn(name = "persona", referencedColumnName = "dni_id")
    private Persona persona;              
}
