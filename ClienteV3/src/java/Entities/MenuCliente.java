/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kmilo
 */
@Entity
@Table(name = "menu_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenuCliente.findAll", query = "SELECT m FROM MenuCliente m"),
    @NamedQuery(name = "MenuCliente.findByCodigo", query = "SELECT m FROM MenuCliente m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MenuCliente.findByNombre", query = "SELECT m FROM MenuCliente m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MenuCliente.findByUrl", query = "SELECT m FROM MenuCliente m WHERE m.url = :url"),
    @NamedQuery(name = "MenuCliente.findByTipo", query = "SELECT m FROM MenuCliente m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "MenuCliente.findByNivel", query = "SELECT m FROM MenuCliente m WHERE m.nivel = :nivel"),
    @NamedQuery(name = "MenuCliente.findByPadre", query = "SELECT m FROM MenuCliente m WHERE m.padre = :padre"),
    @NamedQuery(name = "MenuCliente.findByEstado", query = "SELECT m FROM MenuCliente m WHERE m.estado = :estado")})
public class MenuCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Nombre")
    private String nombre;
    @Size(max = 140)
    @Column(name = "URL")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tipo")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Nivel")
    private int nivel;
    @Column(name = "Padre")
    private Integer padre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Estado")
    private boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoMenu", fetch = FetchType.LAZY)
    private List<PrivilegiosCliente> privilegiosClienteList;

    public MenuCliente() {
    }

    public MenuCliente(Integer codigo) {
        this.codigo = codigo;
    }

    public MenuCliente(Integer codigo, String nombre, int tipo, int nivel, boolean estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.estado = estado;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Integer getPadre() {
        return padre;
    }

    public void setPadre(Integer padre) {
        this.padre = padre;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<PrivilegiosCliente> getPrivilegiosClienteList() {
        return privilegiosClienteList;
    }

    public void setPrivilegiosClienteList(List<PrivilegiosCliente> privilegiosClienteList) {
        this.privilegiosClienteList = privilegiosClienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuCliente)) {
            return false;
        }
        MenuCliente other = (MenuCliente) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.MenuCliente[ codigo=" + codigo + " ]";
    }
    
}
