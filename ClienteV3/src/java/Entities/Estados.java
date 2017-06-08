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
@Table(name = "estados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estados.findAll", query = "SELECT e FROM Estados e"),
    @NamedQuery(name = "Estados.findByIdEstado", query = "SELECT e FROM Estados e WHERE e.idEstado = :idEstado"),
    @NamedQuery(name = "Estados.findByDescripcion", query = "SELECT e FROM Estados e WHERE e.descripcion = :descripcion")})
public class Estados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Estado")
    private Integer idEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado", fetch = FetchType.LAZY)
    private List<Remisiones> remisionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado", fetch = FetchType.LAZY)
    private List<Usuarios> usuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado", fetch = FetchType.LAZY)
    private List<EmpresaOrigen> empresaOrigenList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado", fetch = FetchType.LAZY)
    private List<Clientes> clientesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado", fetch = FetchType.LAZY)
    private List<Objetos> objetosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado", fetch = FetchType.LAZY)
    private List<PersonasSucursal> personasSucursalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado", fetch = FetchType.LAZY)
    private List<Personas> personasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado", fetch = FetchType.LAZY)
    private List<Vehiculos> vehiculosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado", fetch = FetchType.LAZY)
    private List<VehiculosSucursal> vehiculosSucursalList;
    @OneToMany(mappedBy = "estado", fetch = FetchType.LAZY)
    private List<Notificaciones> notificacionesList;

    public Estados() {
    }

    public Estados(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Estados(Integer idEstado, String descripcion) {
        this.idEstado = idEstado;
        this.descripcion = descripcion;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Remisiones> getRemisionesList() {
        return remisionesList;
    }

    public void setRemisionesList(List<Remisiones> remisionesList) {
        this.remisionesList = remisionesList;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @XmlTransient
    public List<EmpresaOrigen> getEmpresaOrigenList() {
        return empresaOrigenList;
    }

    public void setEmpresaOrigenList(List<EmpresaOrigen> empresaOrigenList) {
        this.empresaOrigenList = empresaOrigenList;
    }

    @XmlTransient
    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    @XmlTransient
    public List<Objetos> getObjetosList() {
        return objetosList;
    }

    public void setObjetosList(List<Objetos> objetosList) {
        this.objetosList = objetosList;
    }

    @XmlTransient
    public List<PersonasSucursal> getPersonasSucursalList() {
        return personasSucursalList;
    }

    public void setPersonasSucursalList(List<PersonasSucursal> personasSucursalList) {
        this.personasSucursalList = personasSucursalList;
    }

    @XmlTransient
    public List<Personas> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Personas> personasList) {
        this.personasList = personasList;
    }

    @XmlTransient
    public List<Vehiculos> getVehiculosList() {
        return vehiculosList;
    }

    public void setVehiculosList(List<Vehiculos> vehiculosList) {
        this.vehiculosList = vehiculosList;
    }

    @XmlTransient
    public List<VehiculosSucursal> getVehiculosSucursalList() {
        return vehiculosSucursalList;
    }

    public void setVehiculosSucursalList(List<VehiculosSucursal> vehiculosSucursalList) {
        this.vehiculosSucursalList = vehiculosSucursalList;
    }

    @XmlTransient
    public List<Notificaciones> getNotificacionesList() {
        return notificacionesList;
    }

    public void setNotificacionesList(List<Notificaciones> notificacionesList) {
        this.notificacionesList = notificacionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estados)) {
            return false;
        }
        Estados other = (Estados) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Estados[ idEstado=" + idEstado + " ]";
    }
    
}
