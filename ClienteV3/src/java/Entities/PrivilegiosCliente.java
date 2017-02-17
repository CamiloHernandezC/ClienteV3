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
@Table(name = "Privilegios_Cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrivilegiosCliente.findAll", query = "SELECT p FROM PrivilegiosCliente p"),
    @NamedQuery(name = "PrivilegiosCliente.findById", query = "SELECT p FROM PrivilegiosCliente p WHERE p.id = :id"),
    @NamedQuery(name = "PrivilegiosCliente.findByVer", query = "SELECT p FROM PrivilegiosCliente p WHERE p.ver = :ver")})
public class PrivilegiosCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ver")
    private boolean ver;
    @JoinColumn(name = "Codigo_Menu", referencedColumnName = "Codigo")
    @ManyToOne(fetch = FetchType.LAZY)
    private MenuCliente codigoMenu;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UsuariosCli usuario;

    public PrivilegiosCliente() {
    }

    public PrivilegiosCliente(Long id) {
        this.id = id;
    }

    public PrivilegiosCliente(Long id, boolean ver) {
        this.id = id;
        this.ver = ver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getVer() {
        return ver;
    }

    public void setVer(boolean ver) {
        this.ver = ver;
    }

    public MenuCliente getCodigoMenu() {
        return codigoMenu;
    }

    public void setCodigoMenu(MenuCliente codigoMenu) {
        this.codigoMenu = codigoMenu;
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
        if (!(object instanceof PrivilegiosCliente)) {
            return false;
        }
        PrivilegiosCliente other = (PrivilegiosCliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PrivilegiosCliente[ id=" + id + " ]";
    }
    
}
