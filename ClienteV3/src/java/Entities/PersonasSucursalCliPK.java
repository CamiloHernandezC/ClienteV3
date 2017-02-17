/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author MAURICIO
 */
@Embeddable
public class PersonasSucursalCliPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "Id_Persona")
    private String idPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Sucursal")
    private long sucursal;

    public PersonasSucursalCliPK() {
    }

    public PersonasSucursalCliPK(String idPersona, long sucursal) {
        this.idPersona = idPersona;
        this.sucursal = sucursal;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public long getSucursal() {
        return sucursal;
    }

    public void setSucursal(long sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        hash += (int) sucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonasSucursalCliPK)) {
            return false;
        }
        PersonasSucursalCliPK other = (PersonasSucursalCliPK) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        if (this.sucursal != other.sucursal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PersonasSucursalCliPK[ idPersona=" + idPersona + ", sucursal=" + sucursal + " ]";
    }
    
}
