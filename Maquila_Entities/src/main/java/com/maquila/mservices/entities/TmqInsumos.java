/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquila.mservices.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "tmq_insumos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TmqInsumos.findAll", query = "SELECT t FROM TmqInsumos t"),
    @NamedQuery(name = "TmqInsumos.findByDniId", query = "SELECT t FROM TmqInsumos t WHERE t.dniId = :dniId"),
    @NamedQuery(name = "TmqInsumos.findByDsNombre", query = "SELECT t FROM TmqInsumos t WHERE t.dsNombre = :dsNombre"),
    @NamedQuery(name = "TmqInsumos.findByNmValor", query = "SELECT t FROM TmqInsumos t WHERE t.nmValor = :nmValor"),
    @NamedQuery(name = "TmqInsumos.findByNmUnidades", query = "SELECT t FROM TmqInsumos t WHERE t.nmUnidades = :nmUnidades")})
public class TmqInsumos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dni_id")
    private Integer dniId;
    @Column(name = "ds_nombre")
    private String dsNombre;
    @Column(name = "nm_valor")
    private Integer nmValor;
    @Column(name = "nm_unidades")
    private Integer nmUnidades;

    public TmqInsumos() {
    }

    public TmqInsumos(Integer dniId) {
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

    public Integer getNmValor() {
        return nmValor;
    }

    public void setNmValor(Integer nmValor) {
        this.nmValor = nmValor;
    }

    public Integer getNmUnidades() {
        return nmUnidades;
    }

    public void setNmUnidades(Integer nmUnidades) {
        this.nmUnidades = nmUnidades;
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
        if (!(object instanceof TmqInsumos)) {
            return false;
        }
        TmqInsumos other = (TmqInsumos) object;
        if ((this.dniId == null && other.dniId != null) || (this.dniId != null && !this.dniId.equals(other.dniId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.maquila.mservices.entities.TmqInsumos[ dniId=" + dniId + " ]";
    }
    
}
