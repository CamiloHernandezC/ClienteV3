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
public class InventarioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Material")
    private int material;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Sucursal")
    private int sucursal;

    public InventarioPK() {
    }

    public InventarioPK(int material, int sucursal) {
        this.material = material;
        this.sucursal = sucursal;
    }

    public int getMaterial() {
        return material;
    }

    public void setMaterial(int material) {
        this.material = material;
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
        hash += (int) material;
        hash += (int) sucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventarioPK)) {
            return false;
        }
        InventarioPK other = (InventarioPK) object;
        if (this.material != other.material) {
            return false;
        }
        if (this.sucursal != other.sucursal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.InventarioPK[ material=" + material + ", sucursal=" + sucursal + " ]";
    }
    
}
