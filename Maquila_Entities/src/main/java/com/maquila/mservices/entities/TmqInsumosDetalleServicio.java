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
@Table(name = "tmq_insumos_detalle_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TmqInsumosDetalleServicio.findAll", query = "SELECT t FROM TmqInsumosDetalleServicio t"),
    @NamedQuery(name = "TmqInsumosDetalleServicio.findByDniId", query = "SELECT t FROM TmqInsumosDetalleServicio t WHERE t.dniId = :dniId"),
    @NamedQuery(name = "TmqInsumosDetalleServicio.findByNmIdInsumo", query = "SELECT t FROM TmqInsumosDetalleServicio t WHERE t.nmIdInsumo = :nmIdInsumo"),
    @NamedQuery(name = "TmqInsumosDetalleServicio.findByNmIdDetalleServicio", query = "SELECT t FROM TmqInsumosDetalleServicio t WHERE t.nmIdDetalleServicio = :nmIdDetalleServicio")})
public class TmqInsumosDetalleServicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dni_id")
    private Integer dniId;
    @Column(name = "nm_id_insumo")
    private Integer nmIdInsumo;
    @Column(name = "nm_id_detalle_servicio")
    private Integer nmIdDetalleServicio;

    public TmqInsumosDetalleServicio() {
    }

    public TmqInsumosDetalleServicio(Integer dniId) {
        this.dniId = dniId;
    }

    public Integer getDniId() {
        return dniId;
    }

    public void setDniId(Integer dniId) {
        this.dniId = dniId;
    }

    public Integer getNmIdInsumo() {
        return nmIdInsumo;
    }

    public void setNmIdInsumo(Integer nmIdInsumo) {
        this.nmIdInsumo = nmIdInsumo;
    }

    public Integer getNmIdDetalleServicio() {
        return nmIdDetalleServicio;
    }

    public void setNmIdDetalleServicio(Integer nmIdDetalleServicio) {
        this.nmIdDetalleServicio = nmIdDetalleServicio;
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
        if (!(object instanceof TmqInsumosDetalleServicio)) {
            return false;
        }
        TmqInsumosDetalleServicio other = (TmqInsumosDetalleServicio) object;
        if ((this.dniId == null && other.dniId != null) || (this.dniId != null && !this.dniId.equals(other.dniId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.maquila.mservices.entities.TmqInsumosDetalleServicio[ dniId=" + dniId + " ]";
    }
    
}
