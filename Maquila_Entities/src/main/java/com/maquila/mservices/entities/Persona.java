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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Usuario
 */

@Entity
@Table(name = "tmq_persona")
public class Persona implements Serializable{
    @TableGenerator(
            table ="tmq_sq",
            name ="tmq_pe",//referencia
            pkColumnName="tmq_seq",// columna con el nombre de sequencia
            valueColumnName="tmq_val",// valor de la secuencia actual
            pkColumnValue="tmq_pe_sq",//Nombre de la secuencia
            initialValue = 1,
            allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tmq_pe")
    @Column(name = "dni_id")
    private Integer id;
    
    @Column(name = "ds_documento")
    private String documento;
    
    @Column(name = "fe_registro")
    @Temporal(TemporalType.DATE)
    private Date feRegistro;
    
    @OneToOne(mappedBy = "id")
    private Empleado empleados;
    
    @OneToOne(mappedBy = "id")
    private Cliente cliente;
    
    @OneToOne(mappedBy = "id")
    private TipoDocumento idTipoDocumento;
}
