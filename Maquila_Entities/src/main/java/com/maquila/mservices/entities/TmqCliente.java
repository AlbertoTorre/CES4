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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "tmq_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TmqCliente.findAll", query = "SELECT t FROM TmqCliente t"),
    @NamedQuery(name = "TmqCliente.findByDniId", query = "SELECT t FROM TmqCliente t WHERE t.dniId = :dniId"),
    @NamedQuery(name = "TmqCliente.findByDsCorreo", query = "SELECT t FROM TmqCliente t WHERE t.dsCorreo = :dsCorreo"),
    @NamedQuery(name = "TmqCliente.findByNmTelefono", query = "SELECT t FROM TmqCliente t WHERE t.nmTelefono = :nmTelefono"),
    @NamedQuery(name = "TmqCliente.findByDsDireccion", query = "SELECT t FROM TmqCliente t WHERE t.dsDireccion = :dsDireccion")})
public class TmqCliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "dni_id")
    private Integer dniId;
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

    public TmqCliente() {
    }

    public TmqCliente(Integer dniId) {
        this.dniId = dniId;
    }

    public Integer getDniId() {
        return dniId;
    }

    public void setDniId(Integer dniId) {
        this.dniId = dniId;
    }

    public String getDsCorreo() {
        return dsCorreo;
    }

    public void setDsCorreo(String dsCorreo) {
        this.dsCorreo = dsCorreo;
    }

    public Integer getNmTelefono() {
        return nmTelefono;
    }

    public void setNmTelefono(Integer nmTelefono) {
        this.nmTelefono = nmTelefono;
    }

    public String getDsDireccion() {
        return dsDireccion;
    }

    public void setDsDireccion(String dsDireccion) {
        this.dsDireccion = dsDireccion;
    }

    @XmlTransient
    public Collection<TmqEncabezadoServicio> getTmqEncabezadoServicioCollection() {
        return tmqEncabezadoServicioCollection;
    }

    public void setTmqEncabezadoServicioCollection(Collection<TmqEncabezadoServicio> tmqEncabezadoServicioCollection) {
        this.tmqEncabezadoServicioCollection = tmqEncabezadoServicioCollection;
    }

    public TmqPersona getTmqPersona() {
        return tmqPersona;
    }

    public void setTmqPersona(TmqPersona tmqPersona) {
        this.tmqPersona = tmqPersona;
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
        if (!(object instanceof TmqCliente)) {
            return false;
        }
        TmqCliente other = (TmqCliente) object;
        if ((this.dniId == null && other.dniId != null) || (this.dniId != null && !this.dniId.equals(other.dniId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.maquila.mservices.entities.TmqCliente[ dniId=" + dniId + " ]";
    }
    
}
