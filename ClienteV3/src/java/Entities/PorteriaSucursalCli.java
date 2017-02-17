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
 * @author MAURICIO
 */
@Entity
@Table(name = "Porteria_Sucursal_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PorteriaSucursalCli.findAll", query = "SELECT p FROM PorteriaSucursalCli p"),
    @NamedQuery(name = "PorteriaSucursalCli.findByPorteria", query = "SELECT p FROM PorteriaSucursalCli p WHERE p.porteriaSucursalCliPK.porteria = :porteria"),
    @NamedQuery(name = "PorteriaSucursalCli.findBySucursal", query = "SELECT p FROM PorteriaSucursalCli p WHERE p.porteriaSucursalCliPK.sucursal = :sucursal"),
    @NamedQuery(name = "PorteriaSucursalCli.findByCampo", query = "SELECT p FROM PorteriaSucursalCli p WHERE p.campo = :campo")})
public class PorteriaSucursalCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PorteriaSucursalCliPK porteriaSucursalCliPK;
    @Size(max = 10)
    @Column(name = "campo")
    private String campo;
    @JoinColumn(name = "Porteria", referencedColumnName = "Id_Porteria", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Porterias porterias;
    @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SucursalesCli sucursalesCli;

    public PorteriaSucursalCli() {
    }

    public PorteriaSucursalCli(PorteriaSucursalCliPK porteriaSucursalCliPK) {
        this.porteriaSucursalCliPK = porteriaSucursalCliPK;
    }

    public PorteriaSucursalCli(long porteria, long sucursal) {
        this.porteriaSucursalCliPK = new PorteriaSucursalCliPK(porteria, sucursal);
    }

    public PorteriaSucursalCliPK getPorteriaSucursalCliPK() {
        return porteriaSucursalCliPK;
    }

    public void setPorteriaSucursalCliPK(PorteriaSucursalCliPK porteriaSucursalCliPK) {
        this.porteriaSucursalCliPK = porteriaSucursalCliPK;
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

    public SucursalesCli getSucursalesCli() {
        return sucursalesCli;
    }

    public void setSucursalesCli(SucursalesCli sucursalesCli) {
        this.sucursalesCli = sucursalesCli;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (porteriaSucursalCliPK != null ? porteriaSucursalCliPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PorteriaSucursalCli)) {
            return false;
        }
        PorteriaSucursalCli other = (PorteriaSucursalCli) object;
        if ((this.porteriaSucursalCliPK == null && other.porteriaSucursalCliPK != null) || (this.porteriaSucursalCliPK != null && !this.porteriaSucursalCliPK.equals(other.porteriaSucursalCliPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PorteriaSucursalCli[ porteriaSucursalCliPK=" + porteriaSucursalCliPK + " ]";
    }
    
}
