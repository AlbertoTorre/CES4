/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquila.mservices.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author 
 * 
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
    private Integer id;
    
    private Integer idServicio;
    @OneToMany(mappedBy="Id")//id de la entidad servicio
    private List<Servicio> servicio;
    
    @Column(name = "nm_horas")
    private Integer horas;
    
    @Column(name = "nm_valor_hora")
    private Integer valorHora;
    
    @Column(name = "ds_observacion")
    private String  observacion;
    
    @ManyToMany
    @JoinTable(name="tmq_insumo_detalleservicio",
               joinColumns=@JoinColumn(name="dni_detalle_servicio"),
               inverseJoinColumns=@JoinColumn(name="dni_insumo"))
    private List<Insumo> insumos;
}
