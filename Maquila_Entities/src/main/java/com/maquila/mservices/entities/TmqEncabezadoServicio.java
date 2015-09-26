/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maquila.mservices.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "tmq_encabezado_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TmqEncabezadoServicio.findAll", query = "SELECT t FROM TmqEncabezadoServicio t"),
    @NamedQuery(name = "TmqEncabezadoServicio.findByDniId", query = "SELECT t FROM TmqEncabezadoServicio t WHERE t.dniId = :dniId"),
    @NamedQuery(name = "TmqEncabezadoServicio.findByNmValorTotal", query = "SELECT t FROM TmqEncabezadoServicio t WHERE t.nmValorTotal = :nmValorTotal"),
    @NamedQuery(name = "TmqEncabezadoServicio.findByNmHorasTotalesLaboradas", query = "SELECT t FROM TmqEncabezadoServicio t WHERE t.nmHorasTotalesLaboradas = :nmHorasTotalesLaboradas"),
    @NamedQuery(name = "TmqEncabezadoServicio.findByFeServicio", query = "SELECT t FROM TmqEncabezadoServicio t WHERE t.feServicio = :feServicio")})
public class TmqEncabezadoServicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dni_id")
    private Integer dniId;
    @Column(name = "nm_valor_total")
    private BigInteger nmValorTotal;
    @Column(name = "nm_horas_totales_laboradas")
    private Integer nmHorasTotalesLaboradas;
    @Column(name = "fe_servicio")
    @Temporal(TemporalType.DATE)
    private Date feServicio;
    @JoinColumn(name = "nm_id_cliente", referencedColumnName = "dni_id")
    @ManyToOne
    private TmqCliente nmIdCliente;

    public TmqEncabezadoServicio() {
    }

    public TmqEncabezadoServicio(Integer dniId) {
        this.dniId = dniId;
    }

    public Integer getDniId() {
        return dniId;
    }

    public void setDniId(Integer dniId) {
        this.dniId = dniId;
    }

    public BigInteger getNmValorTotal() {
        return nmValorTotal;
    }

    public void setNmValorTotal(BigInteger nmValorTotal) {
        this.nmValorTotal = nmValorTotal;
    }

    public Integer getNmHorasTotalesLaboradas() {
        return nmHorasTotalesLaboradas;
    }

    public void setNmHorasTotalesLaboradas(Integer nmHorasTotalesLaboradas) {
        this.nmHorasTotalesLaboradas = nmHorasTotalesLaboradas;
    }

    public Date getFeServicio() {
        return feServicio;
    }

    public void setFeServicio(Date feServicio) {
        this.feServicio = feServicio;
    }

    public TmqCliente getNmIdCliente() {
        return nmIdCliente;
    }

    public void setNmIdCliente(TmqCliente nmIdCliente) {
        this.nmIdCliente = nmIdCliente;
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
        if (!(object instanceof TmqEncabezadoServicio)) {
            return false;
        }
        TmqEncabezadoServicio other = (TmqEncabezadoServicio) object;
        if ((this.dniId == null && other.dniId != null) || (this.dniId != null && !this.dniId.equals(other.dniId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.maquila.mservices.entities.TmqEncabezadoServicio[ dniId=" + dniId + " ]";
    }
    
}
