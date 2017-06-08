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
public class TrasladosMaterialPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Remision_Entrada")
    private int remisionEntrada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Remision_Salida")
    private int remisionSalida;

    public TrasladosMaterialPK() {
    }

    public TrasladosMaterialPK(int remisionEntrada, int remisionSalida) {
        this.remisionEntrada = remisionEntrada;
        this.remisionSalida = remisionSalida;
    }

    public int getRemisionEntrada() {
        return remisionEntrada;
    }

    public void setRemisionEntrada(int remisionEntrada) {
        this.remisionEntrada = remisionEntrada;
    }

    public int getRemisionSalida() {
        return remisionSalida;
    }

    public void setRemisionSalida(int remisionSalida) {
        this.remisionSalida = remisionSalida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) remisionEntrada;
        hash += (int) remisionSalida;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrasladosMaterialPK)) {
            return false;
        }
        TrasladosMaterialPK other = (TrasladosMaterialPK) object;
        if (this.remisionEntrada != other.remisionEntrada) {
            return false;
        }
        if (this.remisionSalida != other.remisionSalida) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.TrasladosMaterialPK[ remisionEntrada=" + remisionEntrada + ", remisionSalida=" + remisionSalida + " ]";
    }
    
}
