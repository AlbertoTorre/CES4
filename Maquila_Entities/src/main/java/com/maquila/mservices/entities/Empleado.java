/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquila.mservices.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="TMQ_EMPLEADO")
public class Empleado implements Serializable{
    @Id
    @Column(name="DNI_CEDULA")
    private String Cedula;
    
    
    
}
