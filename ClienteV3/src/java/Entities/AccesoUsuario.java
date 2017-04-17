/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kmilo
 */
@Entity
@Table(name = "acceso_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccesoUsuario.findAll", query = "SELECT a FROM AccesoUsuario a"),
    @NamedQuery(name = "AccesoUsuario.findByIDAccesoUsuario", query = "SELECT a FROM AccesoUsuario a WHERE a.iDAccesoUsuario = :iDAccesoUsuario")})
public class AccesoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_Acceso_Usuario")
    private Integer iDAccesoUsuario;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuarios usuario;
    @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursales sucursal;

    public AccesoUsuario() {
    }

    public AccesoUsuario(Integer iDAccesoUsuario) {
        this.iDAccesoUsuario = iDAccesoUsuario;
    }

    public Integer getIDAccesoUsuario() {
        return iDAccesoUsuario;
    }

    public void setIDAccesoUsuario(Integer iDAccesoUsuario) {
        this.iDAccesoUsuario = iDAccesoUsuario;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Sucursales getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDAccesoUsuario != null ? iDAccesoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccesoUsuario)) {
            return false;
        }
        AccesoUsuario other = (AccesoUsuario) object;
        if ((this.iDAccesoUsuario == null && other.iDAccesoUsuario != null) || (this.iDAccesoUsuario != null && !this.iDAccesoUsuario.equals(other.iDAccesoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.AccesoUsuario[ iDAccesoUsuario=" + iDAccesoUsuario + " ]";
    }
    
}
