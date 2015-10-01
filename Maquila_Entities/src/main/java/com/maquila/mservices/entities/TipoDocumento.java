/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquila.mservices.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

/**
 * @author Usuario
 */
public class TipoDocumento {
    @TableGenerator(
            table ="tmq_sq",
            name ="tmq_td",//referencia
            pkColumnName="tmq_seq",// columna con el nombre de sequencia
            valueColumnName="tmq_val",// valor de la secuencia actual
            pkColumnValue="tmq_td_sq",//Nombre de la secuencia
            initialValue = 1,
            allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tmq_td")
    @Column(name = "dni_id")
    private Integer id;
    
    @Column(name = "ds_nombre")
    private String nombre;
    
    @OneToMany(mappedBy="idTipoDocumento")
    private List<Persona> persona;  
}
