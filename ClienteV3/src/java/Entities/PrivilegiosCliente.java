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
@Table(name = "privilegios_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrivilegiosCliente.findAll", query = "SELECT p FROM PrivilegiosCliente p"),
    @NamedQuery(name = "PrivilegiosCliente.findByIdPrivilegiosCliente", query = "SELECT p FROM PrivilegiosCliente p WHERE p.idPrivilegiosCliente = :idPrivilegiosCliente"),
    @NamedQuery(name = "PrivilegiosCliente.findByVer", query = "SELECT p FROM PrivilegiosCliente p WHERE p.ver = :ver")})
public class PrivilegiosCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Privilegios_Cliente")
    private Integer idPrivilegiosCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ver")
    private boolean ver;
    @JoinColumn(name = "Codigo_Menu", referencedColumnName = "Codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MenuCliente codigoMenu;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuarios usuario;

    public PrivilegiosCliente() {
    }

    public PrivilegiosCliente(Integer idPrivilegiosCliente) {
        this.idPrivilegiosCliente = idPrivilegiosCliente;
    }

    public PrivilegiosCliente(Integer idPrivilegiosCliente, boolean ver) {
        this.idPrivilegiosCliente = idPrivilegiosCliente;
        this.ver = ver;
    }

    public Integer getIdPrivilegiosCliente() {
        return idPrivilegiosCliente;
    }

    public void setIdPrivilegiosCliente(Integer idPrivilegiosCliente) {
        this.idPrivilegiosCliente = idPrivilegiosCliente;
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

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrivilegiosCliente != null ? idPrivilegiosCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrivilegiosCliente)) {
            return false;
        }
        PrivilegiosCliente other = (PrivilegiosCliente) object;
        if ((this.idPrivilegiosCliente == null && other.idPrivilegiosCliente != null) || (this.idPrivilegiosCliente != null && !this.idPrivilegiosCliente.equals(other.idPrivilegiosCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PrivilegiosCliente[ idPrivilegiosCliente=" + idPrivilegiosCliente + " ]";
    }
    
}
