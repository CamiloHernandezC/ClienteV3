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
 * @author MAURICIO
 */
@Entity
@Table(name = "Acceso_Usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccesoUsuario.findAll", query = "SELECT a FROM AccesoUsuario a"),
    @NamedQuery(name = "AccesoUsuario.findById", query = "SELECT a FROM AccesoUsuario a WHERE a.id = :id")})
public class AccesoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SucursalesCli sucursal;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UsuariosCli usuario;

    public AccesoUsuario() {
    }

    public AccesoUsuario(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SucursalesCli getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalesCli sucursal) {
        this.sucursal = sucursal;
    }

    public UsuariosCli getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuariosCli usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccesoUsuario)) {
            return false;
        }
        AccesoUsuario other = (AccesoUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.AccesoUsuario[ id=" + id + " ]";
    }
    
}
