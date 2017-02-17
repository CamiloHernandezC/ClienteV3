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
 * @author MAURICIO
 */
@Embeddable
public class PorteriaSucursalCliPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Porteria")
    private long porteria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Sucursal")
    private long sucursal;

    public PorteriaSucursalCliPK() {
    }

    public PorteriaSucursalCliPK(long porteria, long sucursal) {
        this.porteria = porteria;
        this.sucursal = sucursal;
    }

    public long getPorteria() {
        return porteria;
    }

    public void setPorteria(long porteria) {
        this.porteria = porteria;
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
        hash += (int) porteria;
        hash += (int) sucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PorteriaSucursalCliPK)) {
            return false;
        }
        PorteriaSucursalCliPK other = (PorteriaSucursalCliPK) object;
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
        return "Entities.PorteriaSucursalCliPK[ porteria=" + porteria + ", sucursal=" + sucursal + " ]";
    }
    
}
