/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquila.mservices.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
    @Column(name = "ds_correo")
    private String dsCorreo;
    @Column(name = "nm_telefono")
    private Integer nmTelefono;
    @Column(name = "ds_direccion")
    private String dsDireccion;
    @OneToMany(mappedBy = "nmIdCliente")
    private Collection<TmqEncabezadoServicio> tmqEncabezadoServicioCollection;
    @JoinColumn(name = "dni_id", referencedColumnName = "dni_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TmqPersona tmqPersona;
}
