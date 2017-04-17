/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kmilo
 */
@Entity
@Table(name = "porterias_sucursal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PorteriasSucursal.findAll", query = "SELECT p FROM PorteriasSucursal p"),
    @NamedQuery(name = "PorteriasSucursal.findByPorteria", query = "SELECT p FROM PorteriasSucursal p WHERE p.porteriasSucursalPK.porteria = :porteria"),
    @NamedQuery(name = "PorteriasSucursal.findBySucursal", query = "SELECT p FROM PorteriasSucursal p WHERE p.porteriasSucursalPK.sucursal = :sucursal"),
    @NamedQuery(name = "PorteriasSucursal.findByCampo", query = "SELECT p FROM PorteriasSucursal p WHERE p.campo = :campo")})
public class PorteriasSucursal implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PorteriasSucursalPK porteriasSucursalPK;
    @Size(max = 32)
    @Column(name = "Campo")
    private String campo;
    @JoinColumn(name = "Porteria", referencedColumnName = "Id_Porteria", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Porterias porterias;
    @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursales sucursales;

    public PorteriasSucursal() {
    }

    public PorteriasSucursal(PorteriasSucursalPK porteriasSucursalPK) {
        this.porteriasSucursalPK = porteriasSucursalPK;
    }

    public PorteriasSucursal(int porteria, int sucursal) {
        this.porteriasSucursalPK = new PorteriasSucursalPK(porteria, sucursal);
    }

    public PorteriasSucursalPK getPorteriasSucursalPK() {
        return porteriasSucursalPK;
    }

    public void setPorteriasSucursalPK(PorteriasSucursalPK porteriasSucursalPK) {
        this.porteriasSucursalPK = porteriasSucursalPK;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public Porterias getPorterias() {
        return porterias;
    }

    public void setPorterias(Porterias porterias) {
        this.porterias = porterias;
    }

    public Sucursales getSucursales() {
        return sucursales;
    }

    public void setSucursales(Sucursales sucursales) {
        this.sucursales = sucursales;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (porteriasSucursalPK != null ? porteriasSucursalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PorteriasSucursal)) {
            return false;
        }
        PorteriasSucursal other = (PorteriasSucursal) object;
        if ((this.porteriasSucursalPK == null && other.porteriasSucursalPK != null) || (this.porteriasSucursalPK != null && !this.porteriasSucursalPK.equals(other.porteriasSucursalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PorteriasSucursal[ porteriasSucursalPK=" + porteriasSucursalPK + " ]";
    }
    
}
