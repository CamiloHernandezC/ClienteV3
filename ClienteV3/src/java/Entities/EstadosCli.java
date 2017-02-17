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
@Table(name = "Estados_Cli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadosCli.findAll", query = "SELECT e FROM EstadosCli e"),
    @NamedQuery(name = "EstadosCli.findByIdEstado", query = "SELECT e FROM EstadosCli e WHERE e.idEstado = :idEstado"),
    @NamedQuery(name = "EstadosCli.findByDescripcion", query = "SELECT e FROM EstadosCli e WHERE e.descripcion = :descripcion")})
public class EstadosCli implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado", fetch = FetchType.LAZY)
    private List<PersonasCli> personasCliList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Estado")
    private Long idEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private List<VehiculosCli> vehiculosCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private List<EmpresaOrigenCli> empresaOrigenCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado", fetch = FetchType.LAZY)
    private List<PersonasSucursalCli> personasSucursalCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado", fetch = FetchType.LAZY)
    private List<ClientesCli> clientesCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private List<ObjetosCli> objetosCliList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private List<UsuariosCli> usuariosCliList;

    public EstadosCli() {
    }

    public EstadosCli(Long idEstado) {
        this.idEstado = idEstado;
    }

    public EstadosCli(Long idEstado, String descripcion) {
        this.idEstado = idEstado;
        this.descripcion = descripcion;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
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

    @XmlTransient
    public List<EmpresaOrigenCli> getEmpresaOrigenCliList() {
        return empresaOrigenCliList;
    }

    public void setEmpresaOrigenCliList(List<EmpresaOrigenCli> empresaOrigenCliList) {
        this.empresaOrigenCliList = empresaOrigenCliList;
    }

    @XmlTransient
    public List<PersonasSucursalCli> getPersonasSucursalCliList() {
        return personasSucursalCliList;
    }

    public void setPersonasSucursalCliList(List<PersonasSucursalCli> personasSucursalCliList) {
        this.personasSucursalCliList = personasSucursalCliList;
    }

    @XmlTransient
    public List<ClientesCli> getClientesCliList() {
        return clientesCliList;
    }

    public void setClientesCliList(List<ClientesCli> clientesCliList) {
        this.clientesCliList = clientesCliList;
    }

    @XmlTransient
    public List<ObjetosCli> getObjetosCliList() {
        return objetosCliList;
    }

    public void setObjetosCliList(List<ObjetosCli> objetosCliList) {
        this.objetosCliList = objetosCliList;
    }

    @XmlTransient
    public List<UsuariosCli> getUsuariosCliList() {
        return usuariosCliList;
    }

    public void setUsuariosCliList(List<UsuariosCli> usuariosCliList) {
        this.usuariosCliList = usuariosCliList;
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
        if (!(object instanceof EstadosCli)) {
            return false;
        }
        EstadosCli other = (EstadosCli) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.EstadosCli[ idEstado=" + idEstado + " ]";
    }

    @XmlTransient
    public List<PersonasCli> getPersonasCliList() {
        return personasCliList;
    }

    public void setPersonasCliList(List<PersonasCli> personasCliList) {
        this.personasCliList = personasCliList;
    }
    
}
