/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author MAURICIO
 */
@Embeddable
public class VisitasEsperadasCliPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "Id_Persona")
    private String idPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Sucursal")
    private long idSucursal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Visita")
    @Temporal(TemporalType.DATE)
    private Date fechaVisita;

    public VisitasEsperadasCliPK() {
    }

    public VisitasEsperadasCliPK(String idPersona, long idSucursal, Date fechaVisita) {
        this.idPersona = idPersona;
        this.idSucursal = idSucursal;
        this.fechaVisita = fechaVisita;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Date getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(Date fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        hash += (int) idSucursal;
        hash += (fechaVisita != null ? fechaVisita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VisitasEsperadasCliPK)) {
            return false;
        }
        VisitasEsperadasCliPK other = (VisitasEsperadasCliPK) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        if (this.idSucursal != other.idSucursal) {
            return false;
        }
        if ((this.fechaVisita == null && other.fechaVisita != null) || (this.fechaVisita != null && !this.fechaVisita.equals(other.fechaVisita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.VisitasEsperadasCliPK[ idPersona=" + idPersona + ", idSucursal=" + idSucursal + ", fechaVisita=" + fechaVisita + " ]";
    }
    
}
