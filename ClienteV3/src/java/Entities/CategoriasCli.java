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
 * @author MAURICIO
 */
@Entity
@Table(name = "Categorias_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriasCli.findAll", query = "SELECT c FROM CategoriasCli c"),
    @NamedQuery(name = "CategoriasCli.findByIdCategoria", query = "SELECT c FROM CategoriasCli c WHERE c.idCategoria = :idCategoria"),
    @NamedQuery(name = "CategoriasCli.findByDescripcion", query = "SELECT c FROM CategoriasCli c WHERE c.descripcion = :descripcion")})
public class CategoriasCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "Id_Categoria")
    private String idCategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria", fetch = FetchType.LAZY)
    private List<MarcasCli> marcasCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria", fetch = FetchType.LAZY)
    private List<EntidadesCli> entidadesCliList;
    @OneToMany(mappedBy = "idCategoria", fetch = FetchType.LAZY)
    private List<NotificacionesCli> notificacionesCliList;

    public CategoriasCli() {
    }

    public CategoriasCli(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public CategoriasCli(String idCategoria, String descripcion) {
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<MarcasCli> getMarcasCliList() {
        return marcasCliList;
    }

    public void setMarcasCliList(List<MarcasCli> marcasCliList) {
        this.marcasCliList = marcasCliList;
    }

    @XmlTransient
    public List<EntidadesCli> getEntidadesCliList() {
        return entidadesCliList;
    }

    public void setEntidadesCliList(List<EntidadesCli> entidadesCliList) {
        this.entidadesCliList = entidadesCliList;
    }

    @XmlTransient
    public List<NotificacionesCli> getNotificacionesCliList() {
        return notificacionesCliList;
    }

    public void setNotificacionesCliList(List<NotificacionesCli> notificacionesCliList) {
        this.notificacionesCliList = notificacionesCliList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriasCli)) {
            return false;
        }
        CategoriasCli other = (CategoriasCli) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.CategoriasCli[ idCategoria=" + idCategoria + " ]";
    }
    
}
