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
public class MaterialesSucursalPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Material")
    private int idMaterial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Sucursal")
    private int idSucursal;

    public MaterialesSucursalPK() {
    }

    public MaterialesSucursalPK(int idMaterial, int idSucursal) {
        this.idMaterial = idMaterial;
        this.idSucursal = idSucursal;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMaterial;
        hash += (int) idSucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialesSucursalPK)) {
            return false;
        }
        MaterialesSucursalPK other = (MaterialesSucursalPK) object;
        if (this.idMaterial != other.idMaterial) {
            return false;
        }
        if (this.idSucursal != other.idSucursal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.MaterialesSucursalPK[ idMaterial=" + idMaterial + ", idSucursal=" + idSucursal + " ]";
    }
    
}
