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
import javax.persistence.JoinColumn;
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
@Table(name = "tmq_detalle_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TmqDetalleServicio.findAll", query = "SELECT t FROM TmqDetalleServicio t"),
    @NamedQuery(name = "TmqDetalleServicio.findByDniId", query = "SELECT t FROM TmqDetalleServicio t WHERE t.dniId = :dniId"),
    @NamedQuery(name = "TmqDetalleServicio.findByNmIdEmpleado", query = "SELECT t FROM TmqDetalleServicio t WHERE t.nmIdEmpleado = :nmIdEmpleado"),
    @NamedQuery(name = "TmqDetalleServicio.findByNmIdServicio", query = "SELECT t FROM TmqDetalleServicio t WHERE t.nmIdServicio = :nmIdServicio"),
    @NamedQuery(name = "TmqDetalleServicio.findByNmHoras", query = "SELECT t FROM TmqDetalleServicio t WHERE t.nmHoras = :nmHoras"),
    @NamedQuery(name = "TmqDetalleServicio.findByNmValorHora", query = "SELECT t FROM TmqDetalleServicio t WHERE t.nmValorHora = :nmValorHora"),
    @NamedQuery(name = "TmqDetalleServicio.findByDsObservacion", query = "SELECT t FROM TmqDetalleServicio t WHERE t.dsObservacion = :dsObservacion")})
public class TmqDetalleServicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dni_id")
    private Integer dniId;
    @Column(name = "nm_id_empleado")
    private Integer nmIdEmpleado;
    @Column(name = "nm_id_servicio")
    private Integer nmIdServicio;
    @Column(name = "nm_horas")
    private Integer nmHoras;
    @Column(name = "nm_valor_hora")
    private Integer nmValorHora;
    @Column(name = "ds_observacion")
    private String dsObservacion;
    @JoinColumn(name = "dni_id", referencedColumnName = "dni_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TmqServicios tmqServicios;
    @JoinColumn(name = "dni_id", referencedColumnName = "dni_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TmqEmpleados tmqEmpleados;

    public TmqDetalleServicio() {
    }

    public TmqDetalleServicio(Integer dniId) {
        this.dniId = dniId;
    }

    public Integer getDniId() {
        return dniId;
    }

    public void setDniId(Integer dniId) {
        this.dniId = dniId;
    }

    public Integer getNmIdEmpleado() {
        return nmIdEmpleado;
    }

    public void setNmIdEmpleado(Integer nmIdEmpleado) {
        this.nmIdEmpleado = nmIdEmpleado;
    }

    public Integer getNmIdServicio() {
        return nmIdServicio;
    }

    public void setNmIdServicio(Integer nmIdServicio) {
        this.nmIdServicio = nmIdServicio;
    }

    public Integer getNmHoras() {
        return nmHoras;
    }

    public void setNmHoras(Integer nmHoras) {
        this.nmHoras = nmHoras;
    }

    public Integer getNmValorHora() {
        return nmValorHora;
    }

    public void setNmValorHora(Integer nmValorHora) {
        this.nmValorHora = nmValorHora;
    }

    public String getDsObservacion() {
        return dsObservacion;
    }

    public void setDsObservacion(String dsObservacion) {
        this.dsObservacion = dsObservacion;
    }

    public TmqServicios getTmqServicios() {
        return tmqServicios;
    }

    public void setTmqServicios(TmqServicios tmqServicios) {
        this.tmqServicios = tmqServicios;
    }

    public TmqEmpleados getTmqEmpleados() {
        return tmqEmpleados;
    }

    public void setTmqEmpleados(TmqEmpleados tmqEmpleados) {
        this.tmqEmpleados = tmqEmpleados;
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
        if (!(object instanceof TmqDetalleServicio)) {
            return false;
        }
        TmqDetalleServicio other = (TmqDetalleServicio) object;
        if ((this.dniId == null && other.dniId != null) || (this.dniId != null && !this.dniId.equals(other.dniId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.maquila.mservices.entities.TmqDetalleServicio[ dniId=" + dniId + " ]";
    }
    
}
