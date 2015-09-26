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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tmq_persona")
public class TmqPersona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dni_id")
    private Integer dniId;
    @Column(name = "ds_documento")
    private String dsDocumento;
    @Column(name = "fe_registro")
    @Temporal(TemporalType.DATE)
    private Date feRegistro;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tmqPersona")
    private TmqEmpleados tmqEmpleados;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tmqPersona")
    private TmqCliente tmqCliente;
    @JoinColumn(name = "nm_id_tipo_documento", referencedColumnName = "dni_id")
    @ManyToOne
    private TmqTipoDocumento nmIdTipoDocumento;

    public TmqPersona() {
    }

    public TmqPersona(Integer dniId) {
        this.dniId = dniId;
    }

    public Integer getDniId() {
        return dniId;
    }

    public void setDniId(Integer dniId) {
        this.dniId = dniId;
    }

    public String getDsDocumento() {
        return dsDocumento;
    }

    public void setDsDocumento(String dsDocumento) {
        this.dsDocumento = dsDocumento;
    }

    public Date getFeRegistro() {
        return feRegistro;
    }

    public void setFeRegistro(Date feRegistro) {
        this.feRegistro = feRegistro;
    }

    public TmqEmpleados getTmqEmpleados() {
        return tmqEmpleados;
    }

    public void setTmqEmpleados(TmqEmpleados tmqEmpleados) {
        this.tmqEmpleados = tmqEmpleados;
    }

    public TmqCliente getTmqCliente() {
        return tmqCliente;
    }

    public void setTmqCliente(TmqCliente tmqCliente) {
        this.tmqCliente = tmqCliente;
    }

    public TmqTipoDocumento getNmIdTipoDocumento() {
        return nmIdTipoDocumento;
    }

    public void setNmIdTipoDocumento(TmqTipoDocumento nmIdTipoDocumento) {
        this.nmIdTipoDocumento = nmIdTipoDocumento;
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
        if (!(object instanceof TmqPersona)) {
            return false;
        }
        TmqPersona other = (TmqPersona) object;
        if ((this.dniId == null && other.dniId != null) || (this.dniId != null && !this.dniId.equals(other.dniId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.maquila.mservices.entities.TmqPersona[ dniId=" + dniId + " ]";
    }
    
}
