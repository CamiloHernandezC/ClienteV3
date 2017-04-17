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

/**
 *
 * @author Kmilo
 */
@Embeddable
public class PorteriasSucursalPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Porteria")
    private int porteria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Sucursal")
    private int sucursal;

    public PorteriasSucursalPK() {
    }

    public PorteriasSucursalPK(int porteria, int sucursal) {
        this.porteria = porteria;
        this.sucursal = sucursal;
    }

    public int getPorteria() {
        return porteria;
    }

    public void setPorteria(int porteria) {
        this.porteria = porteria;
    }

    public int getSucursal() {
        return sucursal;
    }

    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) porteria;
        hash += (int) sucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PorteriasSucursalPK)) {
            return false;
        }
        PorteriasSucursalPK other = (PorteriasSucursalPK) object;
        if (this.porteria != other.porteria) {
            return false;
        }
        if (this.sucursal != other.sucursal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PorteriasSucursalPK[ porteria=" + porteria + ", sucursal=" + sucursal + " ]";
    }
    
}
