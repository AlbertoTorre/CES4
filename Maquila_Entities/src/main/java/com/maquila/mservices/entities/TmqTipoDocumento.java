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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "tmq_tipo_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TmqTipoDocumento.findAll", query = "SELECT t FROM TmqTipoDocumento t"),
    @NamedQuery(name = "TmqTipoDocumento.findByDniId", query = "SELECT t FROM TmqTipoDocumento t WHERE t.dniId = :dniId"),
    @NamedQuery(name = "TmqTipoDocumento.findByDsNombre", query = "SELECT t FROM TmqTipoDocumento t WHERE t.dsNombre = :dsNombre")})
public class TmqTipoDocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dni_id")
    private Integer dniId;
    @Column(name = "ds_nombre")
    private String dsNombre;
    @OneToMany(mappedBy = "nmIdTipoDocumento")
    private Collection<TmqPersona> tmqPersonaCollection;

    public TmqTipoDocumento() {
    }

    public TmqTipoDocumento(Integer dniId) {
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

    @XmlTransient
    public Collection<TmqPersona> getTmqPersonaCollection() {
        return tmqPersonaCollection;
    }

    public void setTmqPersonaCollection(Collection<TmqPersona> tmqPersonaCollection) {
        this.tmqPersonaCollection = tmqPersonaCollection;
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
        if (!(object instanceof TmqTipoDocumento)) {
            return false;
        }
        TmqTipoDocumento other = (TmqTipoDocumento) object;
        if ((this.dniId == null && other.dniId != null) || (this.dniId != null && !this.dniId.equals(other.dniId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.maquila.mservices.entities.TmqTipoDocumento[ dniId=" + dniId + " ]";
    }
    
}
