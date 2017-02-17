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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Entidades_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntidadesCli.findAll", query = "SELECT e FROM EntidadesCli e"),
    @NamedQuery(name = "EntidadesCli.findByIdEntidad", query = "SELECT e FROM EntidadesCli e WHERE e.idEntidad = :idEntidad"),
    @NamedQuery(name = "EntidadesCli.findByDescripcion", query = "SELECT e FROM EntidadesCli e WHERE e.descripcion = :descripcion")})
public class EntidadesCli implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "Id_Entidad")
    private String idEntidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEntidad", fetch = FetchType.LAZY)
    private List<VehiculosCli> vehiculosCliList;
    @JoinColumn(name = "Id_Categoria", referencedColumnName = "Id_Categoria")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CategoriasCli idCategoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entidad", fetch = FetchType.LAZY)
    private List<PersonasSucursalCli> personasSucursalCliList;
    @OneToMany(mappedBy = "idEntidad", fetch = FetchType.LAZY)
    private List<NotificacionesCli> notificacionesCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEntidad", fetch = FetchType.LAZY)
    private List<MovDocumentosCli> movDocumentosCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEntidad", fetch = FetchType.LAZY)
    private List<ObjetosCli> objetosCliList;

    public EntidadesCli() {
    }

    public EntidadesCli(String idEntidad) {
        this.idEntidad = idEntidad;
    }

    public EntidadesCli(String idEntidad, String descripcion) {
        this.idEntidad = idEntidad;
        this.descripcion = descripcion;
    }

    public String getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(String idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<VehiculosCli> getVehiculosCliList() {
        return vehiculosCliList;
    }

    public void setVehiculosCliList(List<VehiculosCli> vehiculosCliList) {
        this.vehiculosCliList = vehiculosCliList;
    }

    public CategoriasCli getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(CategoriasCli idCategoria) {
        this.idCategoria = idCategoria;
    }

    @XmlTransient
    public List<PersonasSucursalCli> getPersonasSucursalCliList() {
        return personasSucursalCliList;
    }

    public void setPersonasSucursalCliList(List<PersonasSucursalCli> personasSucursalCliList) {
        this.personasSucursalCliList = personasSucursalCliList;
    }

    @XmlTransient
    public List<NotificacionesCli> getNotificacionesCliList() {
        return notificacionesCliList;
    }

    public void setNotificacionesCliList(List<NotificacionesCli> notificacionesCliList) {
        this.notificacionesCliList = notificacionesCliList;
    }

    @XmlTransient
    public List<MovDocumentosCli> getMovDocumentosCliList() {
        return movDocumentosCliList;
    }

    public void setMovDocumentosCliList(List<MovDocumentosCli> movDocumentosCliList) {
        this.movDocumentosCliList = movDocumentosCliList;
    }

    @XmlTransient
    public List<ObjetosCli> getObjetosCliList() {
        return objetosCliList;
    }

    public void setObjetosCliList(List<ObjetosCli> objetosCliList) {
        this.objetosCliList = objetosCliList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntidad != null ? idEntidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntidadesCli)) {
            return false;
        }
        EntidadesCli other = (EntidadesCli) object;
        if ((this.idEntidad == null && other.idEntidad != null) || (this.idEntidad != null && !this.idEntidad.equals(other.idEntidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.EntidadesCli[ idEntidad=" + idEntidad + " ]";
    }
    
}
