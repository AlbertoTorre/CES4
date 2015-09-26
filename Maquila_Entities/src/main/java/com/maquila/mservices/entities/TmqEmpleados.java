/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquila.mservices.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "tmq_empleados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TmqEmpleados.findAll", query = "SELECT t FROM TmqEmpleados t"),
    @NamedQuery(name = "TmqEmpleados.findByDniId", query = "SELECT t FROM TmqEmpleados t WHERE t.dniId = :dniId"),
    @NamedQuery(name = "TmqEmpleados.findByDsCargo", query = "SELECT t FROM TmqEmpleados t WHERE t.dsCargo = :dsCargo"),
    @NamedQuery(name = "TmqEmpleados.findByFeIngreso", query = "SELECT t FROM TmqEmpleados t WHERE t.feIngreso = :feIngreso"),
    @NamedQuery(name = "TmqEmpleados.findByFeRetiro", query = "SELECT t FROM TmqEmpleados t WHERE t.feRetiro = :feRetiro")})
public class TmqEmpleados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "dni_id")
    private Integer dniId;
    @Column(name = "ds_cargo")
    private String dsCargo;
    @Column(name = "fe_ingreso")
    @Temporal(TemporalType.DATE)
    private Date feIngreso;
    @Column(name = "fe_retiro")
    @Temporal(TemporalType.DATE)
    private Date feRetiro;
    @JoinColumn(name = "dni_id", referencedColumnName = "dni_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TmqPersona tmqPersona;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tmqEmpleados")
    private TmqDetalleServicio tmqDetalleServicio;

    public TmqEmpleados() {
    }

    public TmqEmpleados(Integer dniId) {
        this.dniId = dniId;
    }

    public Integer getDniId() {
        return dniId;
    }

    public void setDniId(Integer dniId) {
        this.dniId = dniId;
    }

    public String getDsCargo() {
        return dsCargo;
    }

    public void setDsCargo(String dsCargo) {
        this.dsCargo = dsCargo;
    }

    public Date getFeIngreso() {
        return feIngreso;
    }

    public void setFeIngreso(Date feIngreso) {
        this.feIngreso = feIngreso;
    }

    public Date getFeRetiro() {
        return feRetiro;
    }

    public void setFeRetiro(Date feRetiro) {
        this.feRetiro = feRetiro;
    }

    public TmqPersona getTmqPersona() {
        return tmqPersona;
    }

    public void setTmqPersona(TmqPersona tmqPersona) {
        this.tmqPersona = tmqPersona;
    }

    public TmqDetalleServicio getTmqDetalleServicio() {
        return tmqDetalleServicio;
    }

    public void setTmqDetalleServicio(TmqDetalleServicio tmqDetalleServicio) {
        this.tmqDetalleServicio = tmqDetalleServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dniId != null ? dniId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmqEmpleados)) {
            return false;
        }
        TmqEmpleados other = (TmqEmpleados) object;
        if ((this.dniId == null && other.dniId != null) || (this.dniId != null && !this.dniId.equals(other.dniId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.maquila.mservices.entities.TmqEmpleados[ dniId=" + dniId + " ]";
    }
    
}
