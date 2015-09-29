/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquila.mservices.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "tmq_servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TmqServicios.findAll", query = "SELECT t FROM TmqServicios t"),
    @NamedQuery(name = "TmqServicios.findByDniId", query = "SELECT t FROM TmqServicios t WHERE t.dniId = :dniId"),
    @NamedQuery(name = "TmqServicios.findByDsNombre", query = "SELECT t FROM TmqServicios t WHERE t.dsNombre = :dsNombre"),
    @NamedQuery(name = "TmqServicios.findByDsObservacion", query = "SELECT t FROM TmqServicios t WHERE t.dsObservacion = :dsObservacion")})
public class TmqServicios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dni_id")
    private Integer dniId;
    @Column(name = "ds_nombre")
    private String dsNombre;
    @Column(name = "ds_observacion")
    private String dsObservacion;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tmqServicios")
    private TmqDetalleServicio tmqDetalleServicio;

    public TmqServicios() {
    }

    public TmqServicios(Integer dniId) {
        this.dniId = dniId;
    }

    public Integer getDniId() {
        return dniId;
    }

    public void setDniId(Integer dniId) {
        this.dniId = dniId;
    }

    public String getDsNombre() {
        return dsNombre;
    }

    public void setDsNombre(String dsNombre) {
        this.dsNombre = dsNombre;
    }

    public String getDsObservacion() {
        return dsObservacion;
    }

    public void setDsObservacion(String dsObservacion) {
        this.dsObservacion = dsObservacion;
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
        if (!(object instanceof TmqServicios)) {
            return false;
        }
        TmqServicios other = (TmqServicios) object;
        if ((this.dniId == null && other.dniId != null) || (this.dniId != null && !this.dniId.equals(other.dniId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.maquila.mservices.entities.TmqServicios[ dniId=" + dniId + " ]";
    }
    
}
