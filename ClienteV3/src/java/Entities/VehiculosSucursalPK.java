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
public class VehiculosSucursalPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Placa")
    private String placa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Sucursal")
    private long idSucursal;

    public VehiculosSucursalPK() {
    }

    public VehiculosSucursalPK(String placa, long idSucursal) {
        this.placa = placa;
        this.idSucursal = idSucursal;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (placa != null ? placa.hashCode() : 0);
        hash += (int) idSucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VehiculosSucursalPK)) {
            return false;
        }
        VehiculosSucursalPK other = (VehiculosSucursalPK) object;
        if ((this.placa == null && other.placa != null) || (this.placa != null && !this.placa.equals(other.placa))) {
            return false;
        }
        if (this.idSucursal != other.idSucursal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.VehiculosSucursalPK[ placa=" + placa + ", idSucursal=" + idSucursal + " ]";
    }
    
}
